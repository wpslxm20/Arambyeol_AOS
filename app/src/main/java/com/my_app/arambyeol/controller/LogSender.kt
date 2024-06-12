package com.my_app.arambyeol.controller

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.my_app.arambyeol.data.LogRequest
import com.my_app.arambyeol.service.LoggingRetrofitObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class SendLog {
    @RequiresApi(Build.VERSION_CODES.O)
    fun sendLog() {
        val logTag = "sendLog"
        val logRequest = LogRequest(LocalDateTime.now().toString())

        try {
            val response = LoggingRetrofitObj.retrofitService?.sendLog(logRequest)?.execute()
            if (response != null) {
                if (response.isSuccessful) {
                    Log.d(logTag, response.body().toString())
                } else {
                    Log.d(logTag, "response is not successful")
                }
            } else {
                Log.d(logTag, "response is null")
            }
        } catch (e: Exception) {
            Log.e(logTag, "Exception occurred", e)
        }
    }
}