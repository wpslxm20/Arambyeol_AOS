package com.my_app.arambyeol.service

import com.my_app.arambyeol.data.LogRequest
import com.my_app.arambyeol.data.MealPlan
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoggingInterface {
    @POST("/loggingAccessTime")
    fun sendLog(@Body log: LogRequest) : Call<Void>
}