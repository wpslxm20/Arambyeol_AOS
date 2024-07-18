package com.my_app.arambyeol.chat.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import com.my_app.arambyeol.chat.repository.ChatPagingSource
import com.my_app.arambyeol.chat.repository.ChatRepository
import com.my_app.arambyeol.chat.viewmodel.state.ChatListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {
    val tag = "ChatViewModel"
//    private val _chatList = MutableStateFlow<PagingData<ChatData>>(PagingData.empty())
//    val chatList: Flow<PagingData<ChatData>> = _chatList
    var chatList: Flow<PagingData<ChatData>>

    init {
        val startDate = LocalDateTime.now()
        val token = App.token_prefs.accessToken ?: ""
        chatList = repository.getChatList(token, startDate)
    }

//    private fun getChatList(startDate: LocalDateTime, size: Int) {
//        viewModelScope.launch {
//            try {
//                repository.getChatList(App.token_prefs.accessToken!!, startDate, size)
//                    .cachedIn(viewModelScope)
//                    .collect { pagingData ->
//                        _chatList.emit(pagingData)
//                    }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

    fun isMessageFromUser(senderId: String): Boolean {
        return senderId == App.token_prefs.deviceUID
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun shouldShowDateLabel(index: Int, chatList: LazyPagingItems<ChatData>): Boolean {
        val currentDate = formatDateString(chatList[index]!!.sendTime)

        return if (index > 0) {
            val prevDate = formatDateString(chatList[index - 1]!!.sendTime)
            currentDate != prevDate
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}