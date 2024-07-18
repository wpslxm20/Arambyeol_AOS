package com.my_app.arambyeol.view

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.my_app.arambyeol.BtnDateView
import com.my_app.arambyeol.R
import com.my_app.arambyeol.data.DateEnum

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainView(navController:NavController, context: Context) {
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
            TitleView.main(navController)
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