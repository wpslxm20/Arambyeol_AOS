package com.my_app.arambyeol.widget.controller

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.data.*
import com.my_app.arambyeol.data.ConstantObj.DINNER
import com.my_app.arambyeol.data.ConstantObj.LUNCH
import com.my_app.arambyeol.data.ConstantObj.MORNING
import com.my_app.arambyeol.data.ConstantObj.TODAY
import com.my_app.arambyeol.data.ConstantObj.TOMORROW
import com.my_app.arambyeol.data.ConstantObj.TOMORROW_MORNING
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class WidgetController(private val context: Context) {
    private val mealPlanFetcher = MealPlanFetcher()

    fun getCurrentDate() : String {
        val sdf = SimpleDateFormat("MM월 dd일 EEEE", Locale.KOREAN)
        return sdf.format(Calendar.getInstance().time)
    }

    // 평일일 때 Courses
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMealTime(): String {
        val currentTime = LocalTime.now()

        val lunchEndTime = MealTimeEnum.WEEKDAY_LUNCH.endTime
        val dinnerEndTime = MealTimeEnum.WEEKDAY_DINNER.endTime
        val midnightTime = LocalTime.of(23, 59)
        val morningEndTime = MealTimeEnum.WEEKDAY_MORNING.endTime

        if (currentTime.isAfter(morningEndTime) && currentTime.isBefore(lunchEndTime)) {
            return LUNCH
        } else if (currentTime.isAfter(lunchEndTime) && currentTime.isBefore(dinnerEndTime)) {
            return DINNER
        } else if (currentTime.isAfter(dinnerEndTime) && currentTime.isBefore(midnightTime)) {
            return TOMORROW_MORNING
        } else if (currentTime.isBefore(morningEndTime)) {
            return MORNING
        }
        Log.d("getMealTime", "null")
        return MORNING
    }

    // 평일일 때 DayPlan
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMealDate(): String {
        val currentTime = LocalTime.now()
        val dinnerEndTime = MealTimeEnum.WEEKDAY_DINNER.endTime
        val midnightTime = LocalTime.of(23, 59)

        if (currentTime.isAfter(dinnerEndTime) && currentTime.isBefore(midnightTime))
            return TOMORROW
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getDayPlan(mealDate: String): List<List<Course>> {
        return if (mealDate.contains(TOMORROW))
            mealPlanFetcher.getDayPlan(context, TOMORROW)
        else mealPlanFetcher.getDayPlan(context, TODAY)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCources(mealTime: String): List<Course>? {
        val mealPlanFetcher = MealPlanFetcher()
        val mealPlan = mealPlanFetcher.fetchMealPlanData()

        return if (mealPlan != null) {
            if (mealTime.contains(MORNING))
                mealPlan.today.morning
            else if (mealTime.contains(LUNCH))
                mealPlan.today.lunch
            else if (mealTime.contains(DINNER))
                mealPlan.today.dinner
            else
                mealPlan.tomorrow.morning
        } else
            null
    }
}