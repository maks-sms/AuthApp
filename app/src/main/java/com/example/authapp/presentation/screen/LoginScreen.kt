package com.example.authapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authapp.presentation.viewmodel.AuthViewModel
import com.example.authapp.R

@Composable
fun LoginScreen(viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val token by viewModel.token.collectAsState()
    val error by viewModel.error.collectAsState()
    val isTokenValid by viewModel.isTokenValid.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (token == null) {
            Button(onClick = {
                keyboardController?.hide()
                viewModel.login(email, password)
            }) {
                Text(stringResource(R.string.login_button))
            }
        } else {
            Button(onClick = {
                keyboardController?.hide()
                viewModel.validateToken(token!!)
            }) {
                Text(stringResource(R.string.validate_token_button))
            }
        }

        if (token != null) {
            Text(stringResource(R.string.token_label, token!!))
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (isTokenValid != null) {
            Text(stringResource(R.string.token_valid_label, isTokenValid.toString()))
        }
        if (error != null) {
            Text(stringResource(R.string.error_label, error!!), color = Color.Red)
        }
    }
}