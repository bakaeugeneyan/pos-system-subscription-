package com.tatsing.possystemsubscription.ui.signin

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsing.possystemsubscription.base.NetworkResult
import com.tatsing.possystemsubscription.base.isNetworkAvailable
import com.tatsing.possystemsubscription.data.entities.user.UserEntity
import com.tatsing.possystemsubscription.data.entities.user.toUserResponse
import com.tatsing.possystemsubscription.domain.model.login.toDB
import com.tatsing.possystemsubscription.domain.repository.local.user.UserRepository
import com.tatsing.possystemsubscription.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val userRepository: UserRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {

            val isConnected = isNetworkAvailable(context)

            if (isConnected) {
                signInUseCase.signInWithEmail(
                    SignInUseCase.Field(
                        email = email,
                        password = password
                    )
                )
                    .collect { result ->
                        when (result) {

                            is NetworkResult.Loading -> {
                                _uiState.value = _uiState.value.copy(isLoading = true)
                            }

                            is NetworkResult.Success -> {
                                val userData = result.data?.user
                                _uiState.value = _uiState.value.copy(user = userData)

                                // Save user to DB after success
                                val userEntity = userData?.toDB()?.copy(
                                    email = email,
                                    password = password
                                )

                                if (userEntity != null) {
                                    val existingUser = userRepository.getUsers()
                                    if (existingUser != null) {
                                        userRepository.deleteUser(existingUser)
                                    }
                                    insertUser(userEntity)
                                }
                            }

                            is NetworkResult.Error -> {
                                _uiState.value = _uiState.value.copy(
                                    isLoading = false,
                                    errorMsg = result.message
                                )
                            }
                        }
                    }
            } else {
                // Offline fallback: fetch from DB
                val localUser = userRepository.getUsers()

                try {
                    if (localUser.email == email && localUser.password == password) {
                        val userResponse = localUser.toUserResponse()
                        _uiState.value = _uiState.value.copy(user = userResponse)
                    } else {
                        _uiState.value = _uiState.value.copy(
                            errorMsg = "Invalid username or password. Please try again."
                        )
                    }
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(
                        errorMsg = "No local data available. Please connect to the internet."
                    )
                }
            }
        }
    }

    private fun insertUser(userEntity: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(userEntity)
        }
    }

    fun clearError() {
        viewModelScope.launch {
            _uiState.value = SignInUiState(errorMsg = null)
        }
    }
}