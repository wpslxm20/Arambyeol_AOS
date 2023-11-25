package com.example.arambyeol.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object TimeView {
    private const val FONT_SIZE = 17

    @Composable
    fun main() {
        timeView()
    }

    @Composable
    private fun timeView() {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "아람 시간표",
                fontSize = FONT_SIZE.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "평일",
                    fontSize = FONT_SIZE.sp,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .weight(3f)
                ) {
                    mealTimeText(meal = "아침", Time = "7시 30분 - 9시")
                    mealTimeText("Take out", "8시 30분 - 9시 30분")
                    mealTimeText("점심", "11시 30분 - 13시 30분")
                    mealTimeText(meal = "저녁", Time = "17시 30분 - 19시")
                }
            }

            Row(
                modifier = Modifier.padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "주말",
                    fontSize = FONT_SIZE.sp,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .weight(3f)
                ) {
                    mealTimeText(meal = "아침", Time = "8시 - 9시")
                    mealTimeText("점심", "12시 - 13시 30분")
                    mealTimeText(meal = "저녁", Time = "17시 30분 - 18시 40분")
                }
            }
        }
    }

    @Composable
    private fun mealTimeText(meal: String, Time: String){
        Row(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(
                text = meal,
                fontSize = FONT_SIZE.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 30.dp)
            )
            Text(
                text = Time,
                fontSize = FONT_SIZE.sp,
                color = Color.Black
            )
        }
    }
}