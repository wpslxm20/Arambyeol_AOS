package com.my_app.arambyeol.chat.data.remote.api

import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.data.remote.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatInterface {
    @POST("signUp")
    fun signUp(@Body deviceUID: DeviceUID) : Call<SignUpResponse>

    @GET("login")
    fun login(
        @Path("deviceId") deviceUID: String
    )
}