package com.example.arambyeol

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.arambyeol.data.Course
import com.example.arambyeol.data.DateEnum
import com.example.arambyeol.data.MealPlan
import com.example.arambyeol.service.RetrofitObj
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ContentView {
    private val mealTimes = listOf("아침", "점심", "저녁")
//    private var menu = getMenu(CoroutineScope(Dispatchers.Main))

    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun main(menu: MealPlan?, selectedDay: MutableState<DateEnum>) {
//        var menu: MealPlan? = null

//        menu = getMenu()
        val dayPlans = when (selectedDay.value) {
            DateEnum.TODAY -> menu?.today
            DateEnum.TOMORROW -> menu?.tomorrow
            DateEnum.AFTER_TOMORROW -> menu?.theDayAfterTomorrow
        }
        val dayPlansList = dayPlans?.let { mutableListOf(it.morning, dayPlans.lunch, dayPlans.dinner) }
        if (dayPlansList != null) {
            mealPlanView(dayPlansList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun mealPlanView(dayPlansList: MutableList<List<Course>>) {

        BoxWithConstraints {
            val screenWidth = this.maxWidth
            val itemWidth = screenWidth / 2

            LazyRow {
                itemsIndexed(dayPlansList) { index, dayPlan ->
                    DayPlanView.main(
                        mealTime = mealTimes[index],
                        courses = dayPlan,
                        modifier = Modifier.width(itemWidth)
                    )
                }
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun getMenu(): MealPlan? {
//        var menu: MealPlan? = null
//        RetrofitObj.retrofitService?.getMenu()?.enqueue(object : Callback<MealPlan> {
//            override fun onResponse(call: Call<MealPlan>, response: Response<MealPlan>) {
//                if (response.body() != null) {
//                    menu = response.body()!!
//                }
//                if (response.body() == null) {
//                    Log.d("getMenu", "response is null")
//                }
//            }
//
//            override fun onFailure(call: Call<MealPlan>, t: Throwable) {
//                t.message?.let { Log.e("getMenu", it) }
//            }
//
//        })
//        return menu
//    }
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun getMenu(scope: CoroutineScope, selectedDay: MutableState<DateEnum>): MealPlan? {
//        var menu: MealPlan? = null
//        scope.launch(Dispatchers.IO) {
//            try {
//                val response = RetrofitObj.retrofitService?.getMenu()?.execute()
//                if (response != null) {
//                    if (response.isSuccessful) {
//                        menu = response.body()
//                        Log.d("getMenu", menu.toString())
//                    } else {
//                        Log.d("getMenu", "response is null")
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("getMenu", e.message ?: "Unknown error")
//            }
//        }
//        return menu
//    }
}