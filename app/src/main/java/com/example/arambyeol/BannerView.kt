package com.example.arambyeol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object BannerView {
    @Composable
    fun main() {
        banner()
    }

    @Composable
    private fun banner() {
        Column(
            modifier = Modifier
                .width(320.dp)
                .height(50.dp)
                .background(color = Color.Blue)
        ) {

        }
    }
}