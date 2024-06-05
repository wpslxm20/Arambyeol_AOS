package com.my_app.arambyeol.widget.service

import android.app.IntentService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.data.AppDatabase
import com.my_app.arambyeol.data.ConstantObj
import com.my_app.arambyeol.data.Course
import com.my_app.arambyeol.widget.WidgetDayPlanReceiver
import com.my_app.arambyeol.widget.controller.WidgetController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WidgetUpdateService: IntentService("WidgetUpdateService") {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)


    override fun onHandleIntent(intent: Intent?) {
        val appContext: Context = applicationContext
        updateAppWidget(appContext)
    }
    private fun fetchDataFromRoomDB(): String {
        // RoomDB 인스턴스를 가져와 데이터를 조회하는 로직 구현
        // 예시 코드이므로 실제 RoomDB 사용법에 맞게 수정 필요
        return "RoomDB에서 조회한 데이터"
    }

    private fun updateAppWidget(context: Context) {
        val db = AppDatabase.getDatabase(context)
        val mealPlanDao = db.mealPlanDao()

        serviceScope.launch {
            Log.d("widget_update_getData", mealPlanDao.getMealPlan().toString())
        }

//
//        // 위젯 UI 업데이트
//        val appWidgetManager = AppWidgetManager.getInstance(this)
//        val allWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(this, YourWidgetProvider::class.java))
//        for (widgetId in allWidgetIds) {
//            val remoteViews = RemoteViews(packageName, R.layout.your_widget_layout)
//            remoteViews.setTextViewText(R.id.yourTextViewId, latestMealPlan.toString()) // 예시: TextView에 식단 정보 표시
//
//            appWidgetManager.updateAppWidget(widgetId, remoteViews)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel() // 서비스 종료 시 Job 취소
    }
}