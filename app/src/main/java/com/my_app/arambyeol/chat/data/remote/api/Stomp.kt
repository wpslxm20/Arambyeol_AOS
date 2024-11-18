package com.my_app.arambyeol.chat.data.remote.api

import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.google.gson.Gson
import com.my_app.arambyeol.chat.data.remote.model.ChatResponse
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger

fun chat() {
    val logger = Logger.getLogger("Main")
    val gson = Gson()

    var stompConnection: Disposable? = null
    var topic: Disposable? = null

    val url = "wss://aramchat.kro.kr:443/ws-stomp"
    val intervalMillis = 5000L
    val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    val stomp = StompClient(client, intervalMillis).apply {
        this@apply.url = url
    }

    // 연결
    stompConnection = stomp.connect().subscribe { event ->
        when (event.type) {
            Event.Type.OPENED -> {
                logger.log(Level.INFO, "WebSocket 연결 성공")

                // 구독
                topic = stomp.join("/sub/ArambyeolChat").subscribe { message ->
                    logger.log(Level.INFO, "받은 메시지: $message")
                    // 받은 메시지 파싱
                    val response = gson.fromJson(message, ChatResponse::class.java)
                    if (response.body != null) {
                        logger.log(Level.INFO, "실제 메시지: ${response.body.data.message}")
                    }
                }

                // 메시지 전송 예시
//                val chatMessage = ChatMessage("UD294n4", "안녕하세요!", "2021-11-08T11:58:20")
//                val messageJson = gson.toJson(chatMessage)
//                stomp.send("/pub/chat", messageJson).subscribe { success ->
//                    if (success) {
//                        logger.log(Level.INFO, "메시지 전송 성공")
//                    } else {
//                        logger.log(Level.SEVERE, "메시지 전송 실패")
//                    }
//                }
            }
            Event.Type.CLOSED -> {
                logger.log(Level.INFO, "WebSocket 연결 종료")
            }
            Event.Type.ERROR -> {
                logger.log(Level.SEVERE, "WebSocket 오류 발생: ${event.exception?.message}")
            }
            else -> {}
        }
    }

    // 채팅 목록 조회 예시
//    val request = Request.Builder()
//        .url("https://aramchat.kro.kr/chatList?start=2024-06-08T15:12:23&page=0&size=5")
//        .addHeader("Authorization", "Bearer 토큰값")
//        .build()
//
//    client.newCall(request).execute().use { response ->
//        if (!response.isSuccessful) throw IOException("Unexpected code $response")
//        logger.log(Level.INFO, "채팅 목록: ${response.body?.string()}")
//    }

    // 프로그램 종료 시 연결 해제
    Runtime.getRuntime().addShutdownHook(Thread {
        topic?.dispose()
        stompConnection?.dispose()
    })
}


