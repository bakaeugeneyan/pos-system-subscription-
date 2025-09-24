package com.tatsing.possystemsubscription.ui.signin

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tatsing.possystemsubscription.R
import com.tatsing.possystemsubscription.components.AlertDialogView
import com.tatsing.possystemsubscription.navgraph.RootScreen
import com.tatsing.possystemsubscription.ui.theme.POSSystemSubscriptionTheme

@Composable
fun SignInScreen(
    navController: NavController, paddingValues: PaddingValues,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val loginUiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // If login is successful, trigger the delay and navigate
    loginUiState.user.let {
        if (loginUiState.user != null) {
            navController.navigate(RootScreen.Main.route) {
                popUpTo(RootScreen.SignIn.route) { inclusive = true }
            }
        }
    }

    loginUiState.errorMsg.let { errorMsg ->
        if (!errorMsg.isNullOrEmpty()) {
            AlertDialogView(
                onDismissRequest = { viewModel.clearError() },
                onConfirmation = { viewModel.clearError() },
                dialogTitle = "Login Failed!",
                dialogText = "$errorMsg",
                positiveBtnText = "",
                negativeBtnText = "OK"
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 700.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = email, onValueChange = {
                    email = it
                },
                label = { Text(stringResource(R.string.email)) },
                singleLine = true, keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility
                    else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = stringResource(R.string.show_hide),
                        )
                    }
                })

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.signInWithEmail(email, password)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !loginUiState.isLoading // Disable the button when loading
            ) {
                if (loginUiState.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(20.dp) // Small size for inside the button
                    )
                } else {
                    Text(
                        text = "Login",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL and Configuration.ORIENTATION_PORTRAIT,
    device = Devices.TABLET,
)

@Composable
fun LoginPreview() {
    POSSystemSubscriptionTheme {
        val navController = rememberNavController()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SignInScreen(navController, innerPadding)
        }
    }
}