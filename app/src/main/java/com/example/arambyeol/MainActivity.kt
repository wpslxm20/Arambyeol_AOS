package com.example.arambyeol

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun MainView() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TitleView.main()
            Box(modifier = Modifier.height(10.dp))
            BtnDateView.main()
            Box(modifier = Modifier.height(30.dp))
            ContentView.main()
            Divider(
                modifier = Modifier.padding(top = 50.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                color = colorResource(id = R.color.bright_gray)
            )
            BottomView.main()
        }
    }
}