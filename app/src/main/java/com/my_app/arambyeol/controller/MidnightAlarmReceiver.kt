package com.my_app.arambyeol.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MidnightAlarmReceiver : BroadcastReceiver() {
    private val mealPlanFetcher = MealPlanFetcher()

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("alarmReceiver", "execute")
        if (intent != null) {
            if (intent.action == "com.my_app.arambyeol.UPDATE_MEAL_PLAN") {
                // 여기에서 RoomDB 업데이트 로직을 실행
                if (context != null) {
                    mealPlanFetcher.updateCourses(context)
                }
            }
        }
    }
}