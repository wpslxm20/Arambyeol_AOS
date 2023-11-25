package com.example.arambyeol.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.arambyeol.data.Course
import com.example.arambyeol.data.DateEnum
import com.example.arambyeol.data.MealPlan

object ContentView {
    private val mealTimes = listOf("아침", "점심", "저녁")

    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun main(menu: MealPlan?, selectedDay: MutableState<DateEnum>) {
        val dayPlans = when (selectedDay.value) {
            DateEnum.TODAY -> menu?.today
            DateEnum.TOMORROW -> menu?.tomorrow
            DateEnum.AFTER_TOMORROW -> menu?.theDayAfterTomorrow
        }
        val dayPlansList = dayPlans?.let { mutableListOf(it.morning, dayPlans.lunch, dayPlans.dinner) }
        if (dayPlansList != null) {
            mealPlanView(dayPlansList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun mealPlanView(dayPlansList: MutableList<List<Course>>) {

        BoxWithConstraints {
            val screenWidth = this.maxWidth
            val itemWidth = screenWidth / 2

            LazyRow {
                itemsIndexed(dayPlansList) { index, dayPlan ->
                    DayPlanView.main(
                        mealTime = mealTimes[index],
                        courses = dayPlan,
                        modifier = Modifier.width(itemWidth)
                    )
                }
            }
        }
    }
}