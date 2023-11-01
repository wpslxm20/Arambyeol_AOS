package com.example.arambyeol

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

object DayPlanView {

    const val FONT_SIZE = 20

    @Composable
    fun main(mealTime: String, dayPlan: DayPlan) {
        dayPlanView(mealTime, dayPlan)
    }

    @Composable
    private fun dayPlanView(mealTime: String, dayPlan: DayPlan) {
        Text(
            text = mealTime,
            modifier = Modifier,
            color = Color.Black,
            fontSize = FONT_SIZE.sp
        )
    }
}