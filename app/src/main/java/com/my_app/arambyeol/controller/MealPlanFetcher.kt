package com.my_app.arambyeol.controller

import android.util.Log
import com.my_app.arambyeol.data.MealPlan
import com.my_app.arambyeol.service.RetrofitObj
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