package com.my_app.arambyeol.widget.view

import android.appwidget.AppWidgetManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
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
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.controller.NetworkChecker
import com.my_app.arambyeol.data.ConstantObj
import com.my_app.arambyeol.widget.WidgetDayPlan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WidgetCourses : GlanceAppWidget() {

    private val widgetItem = WidgetItem()
    private val widgetController = WidgetController()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val dateTime = widgetController.getMealTime()

        // 시간 날짜 조정
        var day: String = if (ConstantObj.TOMORROW in dateTime) ConstantObj.TOMORROW
        else ConstantObj.TODAY
        var time: String = dateTime.substring(dateTime.length - 2)


        val courses = widgetController.getCourses(context, day, time)
        Log.d("widget_update_getData", courses.toString())

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
                    CoursesView(context, courses)
                }
            }
        }
    }

    override val sizeMode = SizeMode.Single

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun CoursesView(context: Context, courses: List<Course>) {
        Log.d("widget_getCourses", courses.toString())

        courses?.let {
            widgetItem.courseLazyView(courses = courses!!)
        }
    }
}
class WidgetCoursesReceiver : GlanceAppWidgetReceiver() {
    private val widgetController = WidgetController()
    private lateinit var networkChecker: NetworkChecker
    private val mealPlanFetcher = MealPlanFetcher()

    override val glanceAppWidget: GlanceAppWidget
        get() = WidgetCourses()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("widget", "onUpdate")
        networkChecker = NetworkChecker(context)

        // RoomDB의 인스턴스가 null이면 서버에서 DB 업데이트
        CoroutineScope(Dispatchers.IO).launch {
            if (widgetController.isMealPlanNull(context) && networkChecker.isInternetAvailable()) {
                mealPlanFetcher.updateCourses(context)
            }

            // ui 업데이트
            refreshAllWidgets(context)
        }
    }

    fun refreshAllWidgets(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val glanceAppWidgetManager = GlanceAppWidgetManager(context)
            val glanceIds = glanceAppWidgetManager.getGlanceIds(WidgetCourses::class.java)

            withContext(Dispatchers.Main) {
                glanceIds.forEach { glanceId ->
                    WidgetCourses().update(context, glanceId)
                }
            }
        }
    }
}