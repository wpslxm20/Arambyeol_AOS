package com.my_app.arambyeol.widget.view

import android.content.Context
import android.os.Build
import android.util.Log
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
import com.my_app.arambyeol.data.ConstantObj


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
////        widgetController = WidgetController(context)
//        var courses by remember(mealTime) {
//            mutableStateOf<List<Course>?>(null)
//        }
//        LaunchedEffect(Unit) {
//            courses = widgetController.getCources(mealTime)
//        }
        Log.d("widget_getCourses", courses.toString())

        courses?.let {
            widgetItem.courseLazyView(courses = courses!!)
        }
    }
}
class WidgetCoursesReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WidgetCourses()
}