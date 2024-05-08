package com.my_app.arambyeol.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object LoggingRetrofitObj {
    private val BASE_URL = "http://was-alb-692266129.ap-northeast-2.elb.amazonaws.com:8080/" //수정

    var retrofitService : LoggingInterface ?= null
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
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .build()

        createRetrofitApi()
    }

    private fun createRetrofitApi() {
        retrofitService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoggingInterface::class.java)
    }
}