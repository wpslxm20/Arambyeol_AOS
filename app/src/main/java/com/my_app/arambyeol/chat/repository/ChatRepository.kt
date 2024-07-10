package com.my_app.arambyeol.chat.repository

import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.api.ChatRetrofitObj
import com.my_app.arambyeol.chat.data.remote.model.ChatListResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

class ChatRepository @Inject constructor(private val service: ChatInterface) {
    fun getChatList(
        startDate: LocalDateTime, page: Int, size:Int
    ): Call<ChatListResponse> {
        val token = App.token_prefs.accessToken ?: "null"
        return service.getChatList(token, startDate, page, size)
    }
}