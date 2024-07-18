package com.my_app.arambyeol.chat.data.remote.model

data class LoginResponse(
    val success: Boolean,
    val errorCode: Int,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val accessToken: String,
    val refreshToken: String
)