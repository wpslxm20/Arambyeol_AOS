package com.my_app.arambyeol.chat.viewmodel.state

import com.my_app.arambyeol.chat.data.remote.model.ChatData
import java.time.LocalDateTime

data class ChatListState(
    val chatList: MutableList<ChatData> = mutableListOf(),
    var page: Int = 0,
    val startDate: LocalDateTime,
    val size: Int = 30
)