package com.my_app.arambyeol.chat.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my_app.arambyeol.chat.data.remote.model.ChatListResponse
import com.my_app.arambyeol.chat.repository.ChatRepository
import com.my_app.arambyeol.chat.viewmodel.state.ChatListState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

sealed interface ChatEvent {
    data class GetChatList(val startDate: LocalDateTime, val page: Int, val size: Int): ChatEvent
}

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {
    val tag = "ChatViewModel"
//    private val _chatList = MutableLiveData<ChatListResponse>()
//    val chatList: LiveData<ChatListResponse> = _chatList
    var chatListState by mutableStateOf(ChatListState())
        private set

    fun onEvent(event: ChatEvent) {
        Log.d("ChatViewModel_onEvent", "execute")
        when(event) {
            is ChatEvent.GetChatList -> getChatList(event.startDate, event.page, event.size)
        }
    }

    private fun getChatList(startDate: LocalDateTime, page:Int, size:Int) {
        val funName = "getChatList"
        val call = repository.getChatList(startDate, page, size)

        call.enqueue(object : Callback<ChatListResponse> {
            override fun onResponse(
                call: Call<ChatListResponse>,
                response: Response<ChatListResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d(tag, "$funName success: ${response.body()}")
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