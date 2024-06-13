package com.my_app.arambyeol.controller

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.my_app.arambyeol.data.AppDatabase
import com.my_app.arambyeol.data.ConstantObj
import com.my_app.arambyeol.data.Converters
import com.my_app.arambyeol.data.Course
import com.my_app.arambyeol.data.DateEnum
import com.my_app.arambyeol.data.DayPlan
import com.my_app.arambyeol.data.MealPlan
import com.my_app.arambyeol.data.MealPlanEntity
import com.my_app.arambyeol.service.RetrofitObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class MealPlanFetcher {
    fun setMidnightAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MidnightAlarmReceiver::class.java).apply {
            action = "com.my_app.arambyeol.UPDATE_MEAL_PLAN"
        }
        // Android M(API 레벨 23) 이상과 그 이하 버전에 대한 처리
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, flags)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 1)
            set(Calendar.MINUTE, 30)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            // 현재 시간이 알람 시간을 지났다면 다음 날로 설정
            if (before(Calendar.getInstance())) {
                add(Calendar.DATE, 1)
            }
        }

        Log.d("alarmReceiver", "setMidnight")
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    suspend fun getDayPlan(context: Context, day: String): List<List<Course>> {
        val db = AppDatabase.getDatabase(context)
        val mealPlanDao = db.mealPlanDao()
        val converters = Converters()
        Log.d("getDayPlan", day)

        val morningPlan = mealPlanDao.getListCourse(day, "아침")?.listCourse
        val lunchPlan = mealPlanDao.getListCourse(day, "점심")?.listCourse
        val dinnerPlan = mealPlanDao.getListCourse(day, "저녁")?.listCourse
        Log.d("getDayPlan", morningPlan.toString())
        Log.d("getDayPlan", lunchPlan.toString())

        val nullCourse = Course("업데이트 예정", null)

        val morningCourses = morningPlan?.let { converters.toCourseList(it) } ?: listOf(nullCourse)
        val lunchCourses = lunchPlan?.let { converters.toCourseList(it) } ?: listOf(nullCourse)
        val dinnerCourses = dinnerPlan?.let { converters.toCourseList(it) } ?: listOf(nullCourse)

        return listOf(morningCourses, lunchCourses, dinnerCourses)
    }

    fun updateCourses(context: Context) {
        // 비동기 처리를 위해 Coroutine 사용
        CoroutineScope(Dispatchers.IO).launch {
            val mealPlan = fetchMealPlanData()
            Log.d("updateCourses", mealPlan.toString())

            val db = AppDatabase.getDatabase(context)
            db.mealPlanDao().deleteAllCourse()

            if (mealPlan != null) {
                // 오늘 메뉴
                insertDayPlan(db, mealPlan.today, ConstantObj.TODAY)
                // 내일 메뉴
                insertDayPlan(db, mealPlan.tomorrow, ConstantObj.TOMORROW)
                // 모레 메뉴
                insertDayPlan(db, mealPlan.theDayAfterTomorrow, ConstantObj.AFTERTOMORROW)

                Log.d("getDB", db.mealPlanDao().getMealPlan().toString())
            }
        }
    }
    suspend fun insertDayPlan(db: AppDatabase, dayPlan: DayPlan, date: String) {
        val converters = Converters()
        // 아침
        val morningCourse = converters.fromCourseList(dayPlan.morning)
        if (morningCourse != null) {
            db.mealPlanDao().insertListCourse(MealPlanEntity(day = date, time = "아침", listCourse = morningCourse))
        }
        // 점심
        val lunchCourse = converters.fromCourseList(dayPlan.lunch)
        if (lunchCourse != null) {
            db.mealPlanDao().insertListCourse(MealPlanEntity(day = date, time = "점심", listCourse = lunchCourse))
        }
        // 저녁
        val dinnerCourse = converters.fromCourseList(dayPlan.dinner)
        if (dinnerCourse != null) {
            db.mealPlanDao().insertListCourse(MealPlanEntity(day = date, time = "저녁", listCourse = dinnerCourse))
        }
    }

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