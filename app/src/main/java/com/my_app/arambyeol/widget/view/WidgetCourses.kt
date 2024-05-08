package com.my_app.arambyeol.widget.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.MediumTopAppBar
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
import com.my_app.arambyeol.widget.WidgetDayPlan


class WidgetCourses : GlanceAppWidget() {

    private val widgetItem = WidgetItem()
    private lateinit var widgetController: WidgetController

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val currentDate = widgetController.getCurrentDate()
        val mealTime = widgetController.getMealTime()
        widgetController = WidgetController(context)

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
                    CoursesView(context, mealTime = mealTime)
                }
            }
        }
    }

    override val sizeMode = SizeMode.Single

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun CoursesView(context: Context,mealTime: String) {
//        widgetController = WidgetController(context)
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
}