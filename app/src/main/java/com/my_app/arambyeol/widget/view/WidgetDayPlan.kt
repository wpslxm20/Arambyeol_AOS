package com.my_app.arambyeol.widget

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.my_app.arambyeol.R
import com.my_app.arambyeol.data.AppDatabase
import com.my_app.arambyeol.data.ConstantObj
import com.my_app.arambyeol.data.Course
import com.my_app.arambyeol.widget.controller.WidgetController
import com.my_app.arambyeol.widget.view.WidgetItem

class WidgetDayPlan(): GlanceAppWidget() {

    private val widgetItem = WidgetItem()
    private val widgetController = WidgetController()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        var day = widgetController.getMealDate()
        if (day == "") day = ConstantObj.TODAY

        val dayPlan = widgetController.getDayPlan(context, day)
        Log.d("widget_update_getData", dayPlan.toString())

        provideContent {
            MyContent(context, dayPlan)
        }
    }

    override val sizeMode = SizeMode.Single

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun MyContent(context: Context, dayPlan: List<List<Course>>) {
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
                val mealDate= widgetController.getMealDate()
                widgetItem.DateView(currentDate, mealDate)

                DayPlanView(context, mealDate, dayPlan)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun DayPlanView(context: Context, mealDate: String, dayPlan: List<List<Course>>) {
        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            dayPlan?.let {
                val modifier = GlanceModifier.defaultWeight()
                CoursesView(modifier, mealTime = "아침", courses = it[0])
                line()
                CoursesView(modifier, mealTime = "점심", courses = it[1])
                line()
                CoursesView(modifier, mealTime = "저녁", courses = it[2])
            }
        }
    }

    @SuppressLint("ResourceType")
    @Composable
    private fun line() {
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(5.dp)
        ) {}
         Box(
             modifier = GlanceModifier
                 .fillMaxWidth()
                 .height(1.dp)
                 .background(colorProvider = ColorProvider(R.color.bright_gray))
         ) {}
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(5.dp)
        ) {}
    }

    @Composable
    private fun CoursesView(modifier: GlanceModifier,mealTime: String, courses: List<Course>) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mealTime,
                modifier = GlanceModifier
                    .padding(start = 30.dp),
                style = TextStyle(
                    fontSize = WidgetItem.FONT_SIZE.sp
                )
            )
            widgetItem.courseLazyView(courses = courses)
        }
    }

}

class WidgetDayPlanReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WidgetDayPlan()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("widget", "onUpdate")
//        val intent = Intent(context, WidgetUpdateService::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService ( intent );
//        } else {
//            context.startService ( intent );
//        }
//        ContextCompat.startForegroundService(context, intent)
    }
}