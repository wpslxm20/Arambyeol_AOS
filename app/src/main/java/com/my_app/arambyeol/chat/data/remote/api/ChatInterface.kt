package com.my_app.arambyeol.chat.data.remote.api

import com.my_app.arambyeol.chat.data.remote.model.ChatListResponse
import com.my_app.arambyeol.chat.data.remote.model.ChatResponseBody
import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.data.remote.model.LoginResponse
import com.my_app.arambyeol.chat.data.remote.model.NicknameResponse
import com.my_app.arambyeol.chat.data.remote.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime

interface ChatInterface {
    @POST("signUp")
    fun signUp(@Body deviceUID: DeviceUID): Call<SignUpResponse>

    @GET("login")
    fun login(
        @Query("deviceId") deviceUID: String
    ): Call<LoginResponse>

    @GET("nickname")
    fun getNickname(
        @Header("Authorization") accessToken: String
    ): Call<NicknameResponse>

    @GET("/chatList")
    fun getChatList(
        @Header("Authorization") accessToken: String,
        @Query("start") startDate: LocalDateTime,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<ChatListResponse>
}