package com.tatsing.possystemsubscription.ui.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsing.possystemsubscription.base.NetworkResult
import com.tatsing.possystemsubscription.base.isNetworkAvailable
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
    @ApplicationContext private val context: Context
): ViewModel(){

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {

            val isConnected = isNetworkAvailable(context)

            if (isConnected) {
                signInUseCase.signInWithEmail(SignInUseCase.Field(email = email, password = password))
                    .collect { result ->
                        when (result) {

                            is NetworkResult.Loading -> {
                                _uiState.value = _uiState.value.copy(isLoading = true)
                            }

                            is NetworkResult.Success -> {
                                val userData = result.data?.user
                                _uiState.value = _uiState.value.copy(user = userData)
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

            }
        }
    }

    fun clearError() {
        viewModelScope.launch {
            _uiState.value = SignInUiState(errorMsg = null)
        }
    }
}