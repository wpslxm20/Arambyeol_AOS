package com.example.arambyeol.controller

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.arambyeol.data.MealPlan
import com.example.arambyeol.service.RetrofitObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealPlanFetcher {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchMealPlanData(): MealPlan? {
        var menu: MealPlan? = null
        withContext(Dispatchers.IO) {
            try {
                val response = RetrofitObj.retrofitService?.getMenu()?.execute()
                if (response != null) {
                    if (response.isSuccessful) {
                        menu = response.body()
                        Log.d("getMenu", menu.toString())
                    } else {
                        Log.d("getMenu", "response is null")
                    }
                } else {
                    Log.d("getMenu", "response is null")
                }
            } catch (e: Exception) {
                Log.e("getMenu", e.message ?: "Unknown error")
            }
        }
        return menu
    }
}