package com.my_app.arambyeol.widget.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.my_app.arambyeol.data.Course

class WidgetItem {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun DateView(currentDate: String, mealTime: String) {
        Row(modifier = GlanceModifier.padding(vertical = 10.dp)) {
            currentDateView(currentDate)
            Box(modifier = GlanceModifier.padding(end = 10.dp)) {}
            mealTimeView(mealTime)
        }
    }

    @Composable
    fun currentDateView(currentDate: String) {
        Text(
            text = currentDate,
            style = TextStyle(
                fontSize = FONT_SIZE.sp
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun mealTimeView(mealTime: String) {
        Text(
            text = mealTime,
            style = TextStyle(
                fontSize = FONT_SIZE.sp
            )
        )
    }


    @Composable
    fun courseLazyView(courses: List<Course>) {
        LazyColumn(
            modifier = GlanceModifier
                .fillMaxWidth()
        ) {
            items(courses) {course ->
                courseView(course = course)
            }
        }
    }

    @Composable
    private fun courseView(course: Course) {
        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            courseNameText(courseName = course.course)
            course.menu?.let { courseMenuLazy(menus = it) }
        }
    }

    @Composable
    private fun courseNameText(courseName: String) {
        Text(
            text = courseName,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = FONT_SIZE.sp
            )
        )
    }

    @Composable
    private fun courseMenuLazy(menus: List<String>) {
        menus.forEach { menu ->
            Text(
                text = menu,
                style = TextStyle(
                    fontSize = FONT_SIZE.sp
                )
            )

        }
    }

    companion object {
        const val FONT_SIZE = 11
    }
}