package com.my_app.arambyeol.chat.data.remote.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    val success: Boolean,
    val errorCode: Int,
    val message: String
)

data class DeviceUID(
    @SerializedName("deviceId")
    val deviceUID: String
)