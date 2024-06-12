package com.my_app.arambyeol

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.ads.MobileAds
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.controller.NetworkChecker
import com.my_app.arambyeol.data.DateEnum
import com.my_app.arambyeol.view.*
import kotlinx.coroutines.*
import com.my_app.arambyeol.controller.SendLog
import com.my_app.arambyeol.data.AppDatabase


class MainActivity : AppCompatActivity() {
    private val mealPlanFetcher = MealPlanFetcher()
    private lateinit var db: AppDatabase
    private val sendLog = SendLog()
    private lateinit var networkChecker: NetworkChecker

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        // 자정에 알람, -> receiver에서 데이터 fetch, roomDB에 저장
        mealPlanFetcher.setMidnightAlarm(this)
        db = AppDatabase.getDatabase(this)
        networkChecker = NetworkChecker(this)

        lifecycleScope.launch {
            val mealPlan = withContext(Dispatchers.IO) {
                db.mealPlanDao().getMealPlan()
            }
            Log.d("onCreate_getMealPlan", mealPlan.toString())
            if ((mealPlan == null || mealPlan.isEmpty()) && networkChecker.isInternetAvailable()) {
                Log.d("onCreate_getMealPlan", "successNetwork")
                mealPlanFetcher.updateCourses(this@MainActivity)
            }
            setContent {
                MainView(this@MainActivity)
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onResume() {
//        super.onResume()
//        sendLog.sendLog()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun MainView(context: Context) {
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
                ContentView.main(context, selectedDay)
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