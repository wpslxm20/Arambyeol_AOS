package com.my_app.arambyeol.chat.viewmodel.state

import com.my_app.arambyeol.chat.data.remote.model.ChatData

data class ChatListState(
    val chatList: List<ChatData> = emptyList()
)