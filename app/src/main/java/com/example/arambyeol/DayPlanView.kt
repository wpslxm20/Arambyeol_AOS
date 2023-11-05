package com.example.arambyeol

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

object DayPlanView {

    private const val MEAL_TIME_FONT_SIZE = 17
    private const val FONT_SIZE = 16

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun main(mealTime: String, courses: List<Course>, modifier: Modifier) {
        dayPlanView(mealTime, courses, modifier)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun dayPlanView(mealTime: String, courses: List<Course>,  modifier: Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            mealTimeTextView(mealTime = mealTime)
            Box(modifier = Modifier.height(30.dp))
            courseBoxView(courses = courses)
        }
    }

    @Composable
    private fun mealTimeTextView(mealTime: String){
        Text(
            text = mealTime,
            modifier = Modifier,
            color = Color.Black,
            fontSize = MEAL_TIME_FONT_SIZE.sp,
            fontWeight = FontWeight.Bold
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun courseBoxView(courses: List<Course>){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(horizontal = 20.dp)
                .drawColoredShadow(
                    color = colorResource(id = R.color.bright_yellow),
                    alpha = 0.4f,
                    borderRadius = 2.dp,
                    offsetX = 0.dp,
                    offsetY = 5.dp
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.bright_yellow).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(20.dp),
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            courseLazyView(courses = courses)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Modifier.drawColoredShadow(
        color: Color,
        alpha: Float = 0.2f,
        borderRadius: Dp = 0.dp,
        shadowRadius: Dp = 20.dp,
        offsetY: Dp = 0.dp,
        offsetX: Dp = 0.dp
    ) = this.drawBehind {
        val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
        val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparentColor
            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }

    @Composable
    private fun courseLazyView(courses: List<Course>) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp, end = 20.dp, bottom = 20.dp)
        ) {
            items(courses){course ->
                courseView(course = course)
            }
        }
    }

    @Composable
    private fun courseView(course: Course) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            courseNameText(courseName = course.courseName)
            courseMenuLazy(menus = course.menu)
        }
    }

    @Composable
    private fun courseNameText(courseName: String) {
        Text(
            text = courseName,
            color = colorResource(id = R.color.dark_gray),
            fontSize = FONT_SIZE.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }

    @Composable
    private fun courseMenuLazy(menus: List<String>) {
        menus.forEach { menu ->
            Text(
                text = menu,
                color = Color.Black,
                fontSize = FONT_SIZE.sp
            )
        }
    }
}