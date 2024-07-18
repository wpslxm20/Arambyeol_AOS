package com.my_app.arambyeol.chat.data.remote.model

// 채팅 받을 때

data class ChatResponseBody(
    val success: Boolean,
    val errorCode: Int,
    val message: String,
    val data: ChatData
)

//채팅 받을 때, data 부분
data class ChatData(
    val senderDid: String,
    val senderNickname: String,
    val chatId: String,
    val message: String,
    val sendTime: String
)

// 이건 일단 생각하지 말자. 나중에 type을 바꿔야 할 듯
data class ChatResponse(
    val headers: Map<String, String>,
    val body: ChatResponseBody,
    val statusCodeValue: Int,
    val statusCode: String
)



// 채팅 보낼 때
data class ChatMessage(
    val senderDid: String,
    val message: String,
    val sendTime: String
)