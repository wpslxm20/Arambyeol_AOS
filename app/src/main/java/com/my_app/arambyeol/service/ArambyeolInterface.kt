package com.my_app.arambyeol.service

import com.my_app.arambyeol.data.MealPlan
import retrofit2.Call
import retrofit2.http.GET

interface ArambyeolInterface {
    @GET("menu")
    fun getMenu(): Call<MealPlan>
}