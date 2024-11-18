package com.my_app.arambyeol.chat.data.remote.model

data class ChatListResponse(
    val success: Boolean,
    val errorCode: Int,
    val message: String,
    val data: List<ChatData>
)