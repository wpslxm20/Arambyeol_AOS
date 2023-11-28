package com.example.arambyeol.widget.view

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
import com.example.arambyeol.R
import com.example.arambyeol.data.Course
import com.example.arambyeol.widget.controller.WidgetController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WidgetCourses: GlanceAppWidget() {

    private val widgetItem = WidgetItem()
    private val widgetController = WidgetController()
    
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MyContent()
        }
    }

    override val sizeMode = SizeMode.Single

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun MyContent() {
        Column (
            modifier = GlanceModifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ImageProvider(R.drawable.shape_corner_rounded)),
        ) {
            Column (modifier = GlanceModifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val currentDate = widgetController.getCurrentDate()
                val mealTime = widgetController.getMealTime()
                widgetItem.DateView(currentDate = currentDate, mealTime = mealTime)
                
                CoursesView(mealTime = mealTime)
            }
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun CoursesView(mealTime: String) {
        var courses by remember {
            mutableStateOf<List<Course>?>(null)
        }
        LaunchedEffect(Unit) {
            courses = withContext(Dispatchers.IO) {
                widgetController.getCources(mealTime)
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