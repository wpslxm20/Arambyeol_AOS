package com.my_app.arambyeol.chat.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.api.chat
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import java.time.LocalDateTime

class ChatPagingSource(
    private val service: ChatInterface,
    private val token: String,
    private val startDate: LocalDateTime
): PagingSource<Int, ChatData>() {
    override fun getRefreshKey(state: PagingState<Int, ChatData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatData> {
//        val page = params.key ?: 1
//        Log.d("ChatPagingSource", "Loading page: $")

//        val token = App.token_prefs.accessToken ?: ""
        return try {
            val currentPage = params.key ?: 1
            val response = service.getChatList(token, startDate, currentPage, params.loadSize)
            val data = response.data

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.e("ChatPagingSource", "Exception: ${e.message}")
            LoadResult.Error(e)
        }
    }
}