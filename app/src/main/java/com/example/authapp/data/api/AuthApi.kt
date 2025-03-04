package com.example.authapp.data.api

import com.example.authapp.domain.model.TokenResponse
import com.example.authapp.domain.model.TokenValidationRequest
import com.example.authapp.domain.model.TokenValidationResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getToken(
        @Field("grant_type") grantType: String,
        @Field("client_secret") clientSecret: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("client_id") clientId: String
    ): TokenResponse

    @POST("token")
    suspend fun validateToken(
        @Body request: TokenValidationRequest
    ): List<TokenValidationResponse>
}