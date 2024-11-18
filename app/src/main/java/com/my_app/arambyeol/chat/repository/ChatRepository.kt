package com.my_app.arambyeol.chat.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.api.ChatRetrofitObj
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import com.my_app.arambyeol.chat.data.remote.model.ChatListResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import retrofit2.Response
import java.security.Policy
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


class ChatRepository @Inject constructor(
    private val service: ChatInterface
) {
//    suspend fun getChatList(
//        startDate: LocalDateTime, size:Int
//    ): Flow<PagingData<ChatData>> =
//        chatRemoteDataSource.getChatList(
//            App.token_prefs.accessToken!!,
//            startDate,
//            size
//        )

    fun getChatList(token: String, startDate: LocalDateTime):
    Flow<PagingData<ChatData>>
    {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ChatPagingSource(service, token, startDate)
            }
        ).flow
    }
}