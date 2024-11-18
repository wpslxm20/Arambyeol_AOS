package com.my_app.arambyeol.chat.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import com.my_app.arambyeol.chat.data.remote.model.ChatListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject


//interface ChatRemoteDataSource {
//    suspend fun getChatList(
//        token: String,
//        startDate: LocalDateTime,
//        size: Int
//    ) : Flow<PagingData<ChatData>>
//}

class ChatRemoteDataSource @Inject constructor(
    private val chatService: ChatInterface
){
    suspend fun getChatList(
        token: String,
        startDate: LocalDateTime,
        page: Int,
        size: Int
    ): List<ChatData> = try {
//        val pagingSourceFactory = {
//            ChatPagingSource(
//                ChatPagingSource()
//            )
//        }
//        return Pager(
//            config = PagingConfig(pageSize = size, initialLoadSize = size),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//            .flowOn(Dispatchers.IO)
        val result = chatService.getChatList(token, startDate, page, size)
        result.data
    }  catch (e: Exception) {
        listOf<ChatData>()
    }
}