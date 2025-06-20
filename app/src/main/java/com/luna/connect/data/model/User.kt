package com.luna.connect.data.model

data class UserResponse(
    val id: Int,
    val username: String?,
    val email: String,
    val bio: String?,
    val profile_pic: String?,
    val is_onboarded: Boolean
)