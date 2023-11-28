package com.example.arambyeol.widget.controller

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.arambyeol.controller.MealPlanFetcher
import com.example.arambyeol.data.DayPlan
import com.example.arambyeol.data.MealPlan
import com.example.arambyeol.data.MealTimeEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class WidgetController {
    fun getCurrentDate() : String {
        val sdf = SimpleDateFormat("MM월 dd일 EEEE", Locale.KOREAN)
        return sdf.format(Calendar.getInstance().time)
    }

    // 평일일 때
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMealTime(): String {
        val currentTime = LocalTime.now()

        val lunchEndTime = MealTimeEnum.WEEKDAY_LUNCH.endTime
        val dinnerEndTime = MealTimeEnum.WEEKDAY_DINNER.endTime
        val tomorrowMorningTime = LocalTime.of(0, 0)
        val morningEndTime = MealTimeEnum.WEEKDAY_MORNING.endTime

        if (currentTime.isAfter(morningEndTime) && currentTime.isBefore(lunchEndTime)) {
            return "점심"
        } else if (currentTime.isAfter(lunchEndTime) && currentTime.isBefore(dinnerEndTime)) {
            return "저녁"
        } else if (currentTime.isAfter(dinnerEndTime) && currentTime.isBefore(tomorrowMorningTime)) {
            return "내일 아침"
        } else if (currentTime.isAfter(tomorrowMorningTime) && currentTime.isBefore(morningEndTime)) {
            return "아침"
        }

        return "아침"
    }

    // 평일일 때
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMealDate(): String {
        val currentTime = LocalTime.now()
        val dinnerEndTime = MealTimeEnum.WEEKDAY_DINNER.endTime
        val midnightTime = LocalTime.of(0, 0)

        if (currentTime.isAfter(dinnerEndTime) && currentTime.isBefore(midnightTime))
            return "내일"
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getDayPlan(mealDate: String): DayPlan? {
        val mealPlanFetcher = MealPlanFetcher()
        val mealPlan = mealPlanFetcher.fetchMealPlanData()

        if (mealPlan != null) {
            return if (mealDate.contains("내일"))
                mealPlan.tomorrow
            else
                mealPlan.today
        }
        else {
            return null
        }
    }
}