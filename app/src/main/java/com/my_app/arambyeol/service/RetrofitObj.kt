package com.my_app.arambyeol.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObj {
    private val BASE_URL = "http://arambyeol.kro.kr/"

    var retrofitService : ArambyeolInterface ?= null
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
            .create(ArambyeolInterface::class.java) //자바코드의 class 함수 사용 방법
    }
}