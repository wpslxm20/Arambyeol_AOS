package com.my_app.arambyeol.chat.repository

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.data.remote.model.LoginResponse
import com.my_app.arambyeol.chat.data.remote.model.NicknameResponse
import com.my_app.arambyeol.chat.data.remote.model.SignUpResponse
import retrofit2.Call

class UserRepository(private val service: ChatInterface) {
    fun signUp(deviceUID: DeviceUID): Call<SignUpResponse> {
        return service.signUp(deviceUID)
    }

    fun login(deviceUID: DeviceUID): Call<LoginResponse> {
        return service.login(deviceUID.deviceUID)
    }

    fun saveToken(accessToken: String, refreshToken: String) {
        App.token_prefs.accessToken = "Bearer $accessToken"
        App.token_prefs.refreshToken = "Bearer $refreshToken"
    }

    fun getNickname(): Call<NicknameResponse> {
        val token = App.token_prefs.accessToken ?: "null"
        return service.getNickname(token)
    }

}