package com.example.authapp.data.repository

import com.example.authapp.data.AuthConfig
import com.example.authapp.data.api.AuthApi
import com.example.authapp.domain.model.TokenValidationRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val authConfig: AuthConfig
) {
    suspend fun login(email: String, password: String) = authApi.getToken(
        grantType = authConfig.grantType,
        clientSecret = authConfig.clientSecret,
        username = email,
        password = password,
        clientId = authConfig.clientId
    )

    suspend fun validateToken(token: String): Boolean {
        val response = authApi.validateToken(TokenValidationRequest(token))
        return response.any { it.token == "OK" }
    }
}