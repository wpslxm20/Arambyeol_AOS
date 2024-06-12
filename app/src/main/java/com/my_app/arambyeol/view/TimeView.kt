package com.my_app.arambyeol.view

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.my_app.arambyeol.data.ConstantObj.ARAM_SCHEDULE
import com.my_app.arambyeol.data.ConstantObj.DINNER
import com.my_app.arambyeol.data.ConstantObj.LUNCH
import com.my_app.arambyeol.data.ConstantObj.MORNING
import com.my_app.arambyeol.data.ConstantObj.TAKE_OUT
import com.my_app.arambyeol.data.ConstantObj.WEEKDAY
import com.my_app.arambyeol.data.ConstantObj.WEEKEND
import com.my_app.arambyeol.data.MealTimeEnum
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeView {
    private const val FONT_SIZE = 17

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun main() {
        timeView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun timeView() {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ARAM_SCHEDULE,
                fontSize = FONT_SIZE.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = WEEKDAY,
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
                    mealTimeText(meal = MORNING, Time = "${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKDAY_MORNING.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKDAY_MORNING.endTime)}")
                    mealTimeText(TAKE_OUT, "${formatLocalTimeWithHourOnly(MealTimeEnum.TAKE_OUT.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.TAKE_OUT.endTime)}")
                    mealTimeText(LUNCH, "${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKDAY_LUNCH.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKDAY_LUNCH.endTime)}")
                    mealTimeText(DINNER, Time = "${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKDAY_DINNER.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKDAY_DINNER.endTime)}")
                }
            }

            Row(
                modifier = Modifier.padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = WEEKEND,
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
                    mealTimeText(meal = MORNING, Time = "${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKEND_MORNING.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKEND_MORNING.endTime)}")
                    mealTimeText(LUNCH, "${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKEND_LUNCH.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKEND_LUNCH.endTime)}")
                    mealTimeText(DINNER, Time = "${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKEND_DINNER.startTime)} - ${formatLocalTimeWithHourOnly(MealTimeEnum.WEEKEND_DINNER.endTime)}")
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatLocalTimeWithHourOnly(time: LocalTime): String {
        val isOnTheHour = (time.minute == 0)
        val formatter = if (isOnTheHour) {
            DateTimeFormatter.ofPattern("H시")
        } else {
            DateTimeFormatter.ofPattern("H시 m분")
        }
        return time.format(formatter)
    }
}