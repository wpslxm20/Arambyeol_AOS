package com.my_app.arambyeol.chat.data.remote.api

import com.my_app.arambyeol.service.ArambyeolInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatRetrofitObj {
    private val BASE_URL = "https://aramchat.kro.kr/"

    var retrofitService : ChatInterface?= null
    private val client: OkHttpClient

    //초기화
    init {
        //로그 설정
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Connection", "close")
                    .build()
                chain.proceed(request)
            }
            .build()

        createRetrofitApi()
    }

    private fun createRetrofitApi() {
        retrofitService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatInterface::class.java)
    }
}