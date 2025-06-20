package com.luna.connect.data.api

import com.luna.connect.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun register(@Body body: RegisterRequest): Response<ApiResponse<MessageResponse>>

    @POST("login")
    suspend fun login(@Body body: LoginRequest): Response<ApiResponse<MessageResponse>>

    @POST("verify-otp")
    suspend fun verifyOtp(@Body body: OtpRequest): Response<ApiResponse<MessageResponse>>

    @Multipart
    @POST("complete-onboarding")
    suspend fun completeOnboarding(
        @Part("username") username: RequestBody,
        @Part("bio") bio: RequestBody,
        @Part profilePic: MultipartBody.Part?,
    ): Response<ApiResponse<MessageResponse>>

    @GET("me")
    suspend fun getMe(): Response<ApiResponse<UserResponse>>
}