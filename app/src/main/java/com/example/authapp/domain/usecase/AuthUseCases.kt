package com.example.authapp.domain.usecase

import com.example.authapp.data.repository.AuthRepository
import javax.inject.Inject

class AuthUseCases @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun login(email: String, password: String) = authRepository.login(email, password)
    suspend fun validateToken(token: String): Boolean {
        return authRepository.validateToken(token)
    }
}