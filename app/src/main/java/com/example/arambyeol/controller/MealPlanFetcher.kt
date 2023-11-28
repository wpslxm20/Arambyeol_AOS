package com.example.arambyeol.controller

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.arambyeol.data.MealPlan
import com.example.arambyeol.service.RetrofitObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealPlanFetcher {
    suspend fun fetchMealPlanData(): MealPlan? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitObj.retrofitService?.getMenu()?.execute()
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.d("getMenu", response.body().toString())
                        response.body()
                    }
                    else {
                        Log.d("getMenu", "response is not successful")
                        null
                    }
                } else {
                    Log.d("getMenu", "response is null")
                    null
                }
            } catch (e: Exception) {
                Log.e("getMenu", e.message ?: "Unknown error")
                null
            }
        }
    }
}