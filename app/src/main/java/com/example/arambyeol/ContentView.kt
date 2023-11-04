package com.example.arambyeol

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object ContentView {
    private val mealTimes = listOf("아침", "점심", "저녁")
    private val dayPlans_ = DummyData.dummyMealPlan.today
    private val dayPlans = mutableListOf(dayPlans_.morning, dayPlans_.lunch, dayPlans_.dinner)

    @Composable
    fun main() {
        mealPlanView()
    }

    @Composable
    private fun mealPlanView() {
        BoxWithConstraints {
            val screenWidth = this.maxWidth
            val itemWidth = screenWidth / 2

            LazyRow {
                itemsIndexed(dayPlans) { index, dayPlan ->
                    DayPlanView.main(mealTime = mealTimes[index], courses = dayPlan, modifier = Modifier.width(itemWidth))
                }
            }
        }
    }
}