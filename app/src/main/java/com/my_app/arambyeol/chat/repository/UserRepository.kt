package com.my_app.arambyeol.chat.repository

import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.data.remote.model.SignUpResponse
import retrofit2.Call

class UserRepository(private val service: ChatInterface) {
    fun signUp(deviceUID: DeviceUID): Call<SignUpResponse> {
        return service.signUp(deviceUID)
    }

//    fun login(deviceUID: DeviceUID): Response<>
}