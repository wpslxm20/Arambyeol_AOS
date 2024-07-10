package com.my_app.arambyeol.chat.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import com.my_app.arambyeol.chat.data.remote.model.ChatListResponse
import com.my_app.arambyeol.chat.repository.ChatRepository
import com.my_app.arambyeol.chat.viewmodel.state.ChatListState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {
    val tag = "ChatViewModel"
    var chatListState by mutableStateOf(ChatListState())
        private set

    fun getChatList(startDate: LocalDateTime, page:Int, size:Int) {
        val funName = "getChatList"
        val call = repository.getChatList(startDate, page, size)

        call.enqueue(object : Callback<ChatListResponse> {
            override fun onResponse(
                call: Call<ChatListResponse>,
                response: Response<ChatListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        chatListState = chatListState.copy(chatList = it.data)
                        Log.d(tag, "$funName success: $it")
                    } ?: run {
                        Log.d(tag, "$funName fail: Empty response body")
                    }
                }
                else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Log.d(tag, "$funName fail: $errorMessage")
                }
            }
            override fun onFailure(call: Call<ChatListResponse>, t: Throwable) {
                Log.e(tag, "$funName exception: ${t.message}")
            }
        })
    }

    fun isMessageFromUser(senderId: String): Boolean {
        return senderId == App.token_prefs.deviceUID
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun shouldShowDateLabel(index: Int, chatList: List<ChatData>): Boolean {
        val currentDate = formatDateString(chatList[index].sendTime)

        return if (index < chatList.size - 1) {
            val nextDate = formatDateString(chatList[index + 1].sendTime)
            currentDate != nextDate
        } else {
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateString(nonFormatDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
        val nonFormatDateTime = LocalDateTime.parse(nonFormatDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        return nonFormatDateTime.format(formatter)
    }
}

class ChatViewModelFactory(private val repository: ChatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}