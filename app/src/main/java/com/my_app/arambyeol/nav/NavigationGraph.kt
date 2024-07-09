package com.my_app.arambyeol.nav

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.my_app.arambyeol.chat.view.ChatView
import com.my_app.arambyeol.chat.view.ChatViewRoot
import com.my_app.arambyeol.view.MainView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun setNavigationGraph(
    context: Context
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.Home.route) {
        composable(route = NavItem.Home.route) {
            MainView(navController, context = context)
        }
        composable(route = NavItem.Chat.route) {
            ChatViewRoot(navController)
        }
    }
}