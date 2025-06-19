package com.luna.connect.data.model

data class LoginRequest(
    val email: String,
    val password: String
)


data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class OtpRequest(
    val email: String,
    val otp: String
)

data class ApiResponse<T>(
    val status: String,
    val data: T?,
    val message: String? = null
)

data class MessageResponse(
    val message: String
)

data class TokenResponse(
    val token: String,
    val refresh_token: String? = null
)
