package com.my_app.arambyeol.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.data.AppDatabase
import com.my_app.arambyeol.data.Course
import com.my_app.arambyeol.data.DateEnum
import com.my_app.arambyeol.data.MealPlan
import com.my_app.arambyeol.data.MealPlanDao
import kotlinx.coroutines.launch

object ContentView {
    private val mealTimes = listOf("아침", "점심", "저녁")
    private val mealPlanFetcher = MealPlanFetcher()

    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun main(context: Context, selectedDay: MutableState<DateEnum>) {
        val coroutineScope = rememberCoroutineScope()

        // UI 상태를 위한 상태 변수
        val dayPlansListState = remember { mutableStateOf<List<List<Course>>>(listOf()) }

        // 선택된 날짜가 변경될 때마다 LaunchedEffect를 사용하여 코루틴 시작
        LaunchedEffect(selectedDay.value) {
            coroutineScope.launch {
                val dayPlansList = mealPlanFetcher.getDayPlan(context, selectedDay.value.date)
                dayPlansListState.value = dayPlansList // 상태 업데이트
            }
        }

        // UI 렌더링에는 상태 변수 사용
        mealPlanView(dayPlansList = dayPlansListState.value)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun mealPlanView(dayPlansList: List<List<Course>>) {

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