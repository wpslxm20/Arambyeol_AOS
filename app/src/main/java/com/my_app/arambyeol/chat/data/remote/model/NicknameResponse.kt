package com.my_app.arambyeol.chat.data.remote.model

import com.google.gson.annotations.SerializedName

data class NicknameResponse(
    val success: Boolean,
    val errorCode: Int,
    val message: String,
    val data: NicknameData
)

data class NicknameData(
    @SerializedName("deviceId")
    val deviceUID: String,
    val nickname: String
)