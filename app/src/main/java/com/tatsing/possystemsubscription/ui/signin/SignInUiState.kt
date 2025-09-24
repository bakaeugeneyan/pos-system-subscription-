package com.tatsing.possystemsubscription.ui.signin

import com.tatsing.possystemsubscription.domain.model.login.User

data class SignInUiState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val user: User? = null
)