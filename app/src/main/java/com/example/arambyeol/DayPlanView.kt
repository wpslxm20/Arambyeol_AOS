package com.example.arambyeol

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object DayPlanView {

    private const val FONT_SIZE = 20

    @Composable
    fun main(mealTime: String, courses: List<Course>, modifier: Modifier) {
        dayPlanView(mealTime, courses, modifier)
    }

    @Composable
    private fun dayPlanView(mealTime: String, courses: List<Course>,  modifier: Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            mealTimeTextView(mealTime = mealTime)
            courseBoxView(courses = courses)
        }
    }

    @Composable
    private fun mealTimeTextView(mealTime: String){
        Text(
            text = mealTime,
            modifier = Modifier,
            color = Color.Black,
            fontSize = FONT_SIZE.sp,
            fontWeight = FontWeight.Bold
        )
    }

    @Composable
    private fun courseBoxView(courses: List<Course>){

    }

    @Composable
    private fun courseTextView(courseName : String){

    }
}