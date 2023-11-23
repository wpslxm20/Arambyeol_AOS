package com.example.arambyeol.service

import com.example.arambyeol.data.MealPlan
import retrofit2.Call
import retrofit2.http.GET

interface ArambyeolInterface {
    @GET("menu")
    fun getMenu(): Call<MealPlan>
}