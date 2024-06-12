package com.my_app.arambyeol.data

import java.time.LocalDateTime

data class LogRequest(
    val accessTime: String,
    val platformType: String = "AOS"
)
