package com.example.authapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenValidationResponse(
    val token: String
)