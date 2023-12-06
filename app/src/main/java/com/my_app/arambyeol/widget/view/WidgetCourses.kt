package com.my_app.arambyeol.widget.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import com.my_app.arambyeol.data.Course
import com.my_app.arambyeol.widget.controller.WidgetController
import java.util.*
import com.my_app.arambyeol.R


class WidgetCourses : GlanceAppWidget() {

    private val widgetItem = WidgetItem()
    private val widgetController = WidgetController()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val currentDate = widgetController.getCurrentDate()
        val mealTime = widgetController.getMealTime()

        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(ImageProvider(R.drawable.shape_corner_rounded)),
            ) {
                Column(
                    modifier = GlanceModifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    widgetItem.DateView(currentDate = currentDate, mealTime = mealTime)
                    CoursesView(mealTime = mealTime)
                }
            }
        }
    }

    override val sizeMode = SizeMode.Single

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun CoursesView(mealTime: String) {
        var courses by remember(mealTime) {
            mutableStateOf<List<Course>?>(null)
        }
        LaunchedEffect(Unit) {
            for (i in 1..10) {
                courses = widgetController.getCources(mealTime)
                if (courses != null) break
            }
        }
        Log.d("widget_getCources", courses.toString())

        courses?.let {
            widgetItem.courseLazyView(courses = courses!!)
        }
    }
}

class WidgetCoursesReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WidgetCourses()

    private val TAG = "WidgetCoursesReceiver"
    private var alarmManager: AlarmManager? = null
    private var pIntent: PendingIntent? = null

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        val action: String? = intent.action

        // 위젯 업데이트 인텐트를 수신했을 때
        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE"))
        {
            Log.w(TAG, "android.appwidget.action.APPWIDGET_UPDATE")
            removePreviousAlarm()

            val midnightTime = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, 16)
                set(Calendar.MINUTE, 14)
            }
            pIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

            if (pIntent != null) {
                alarmManager?.set(AlarmManager.RTC, midnightTime.timeInMillis, pIntent!!)
            }

        }
        // 위젯 제거 인텐트를 수신했을 때
        else if (action.equals("android.appwidget.action.APPWIDGET_DISABLED")) {
            Log.w(TAG, "android.appwidget.action.APPWIDGET_DISABLED")
            removePreviousAlarm();
        }

    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    fun removePreviousAlarm()
    {
        if (alarmManager != null && pIntent != null)
        {
            pIntent!!.cancel()
            alarmManager!!.cancel(pIntent!!)
        }
    }
}