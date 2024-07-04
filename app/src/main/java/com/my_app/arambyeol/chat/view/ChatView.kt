package com.my_app.arambyeol.chat.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.my_app.arambyeol.chat.chat

@Composable
fun ChatView(navController: NavHostController) {
    Text("ChatView")
    chat()
}