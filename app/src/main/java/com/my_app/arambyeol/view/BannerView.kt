package com.my_app.arambyeol.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object BannerView {
    @Composable
    fun main() {
//        banner()
    }

    @Composable
    private fun banner() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp)
                    .background(color = Color.Blue),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "광고 넣을 곳",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }

    }
}