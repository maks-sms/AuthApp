package com.example.authapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val _isTokenValid = MutableStateFlow<Boolean?>(null)
    val isTokenValid: StateFlow<Boolean?> get() = _isTokenValid

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authUseCases.login(email, password)
                _token.value = response.access_token
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun validateToken(token: String) {
        if (token.isBlank()) {
            _error.value = "Токен отсутствует"
            return
        }

        viewModelScope.launch {
            try {
                val isValid = authUseCases.validateToken(token)
                _isTokenValid.value = isValid
            } catch (e: Exception) {
                _isTokenValid.value = false
            }
        }
    }
}