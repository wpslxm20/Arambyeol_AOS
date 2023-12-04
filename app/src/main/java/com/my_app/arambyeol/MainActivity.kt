package com.my_app.arambyeol

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.data.DateEnum
import com.my_app.arambyeol.data.MealPlan
import com.my_app.arambyeol.view.*
import kotlinx.coroutines.*
import com.my_app.arambyeol.R


class MainActivity : AppCompatActivity() {
    private val mealPlanFetcher = MealPlanFetcher()

    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val menu = remember { mutableStateOf<MealPlan?>(null) }

            lifecycleScope.launch {
                menu.value = mealPlanFetcher.fetchMealPlanData()
            }

            MainView(menu.value)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun MainView(menu: MealPlan?) {
        val selectedDay = remember { mutableStateOf(DateEnum.TODAY) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .weight(1f)
            ) {
                TitleView.main()
                Box(modifier = Modifier.height(10.dp))
                BtnDateView.main(selectedDay)
                Box(modifier = Modifier.height(30.dp))
                ContentView.main(menu, selectedDay)
                Divider(
                    modifier = Modifier.padding(top = 50.dp, bottom = 50.dp, start = 20.dp, end = 20.dp),
                    color = colorResource(id = R.color.bright_gray)
                )
                TimeView.main()
                Divider(
                    modifier = Modifier.padding(top = 30.dp, bottom = 30.dp, start = 20.dp, end = 20.dp),
                    color = colorResource(id = R.color.bright_gray)
                )
                BottomView.main()
                Box(modifier = Modifier.height(10.dp))
            }
            BannerView.main()
        }

    }
}