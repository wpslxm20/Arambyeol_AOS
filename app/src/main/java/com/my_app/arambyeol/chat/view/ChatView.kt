package com.my_app.arambyeol.chat.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.my_app.arambyeol.chat.data.remote.api.chat
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import com.my_app.arambyeol.chat.viewmodel.ChatEvent
import com.my_app.arambyeol.chat.viewmodel.ChatViewModel
import com.my_app.arambyeol.chat.viewmodel.state.ChatListState
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatViewRoot(navController: NavHostController) {
    val viewModel = hiltViewModel<ChatViewModel>()
    ChatView(
        navController,
        state = viewModel.chatListState,
        onEvent = viewModel::onEvent
    )
    Log.d("ChatViewRoot", "execute")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatView(
    navController: NavHostController,
    state: ChatListState,
    onEvent: (ChatEvent) -> Unit
) {
    val startDate = remember { LocalDateTime.now() }
    val page = remember { 1 }
    val size = remember { 30 }

    LaunchedEffect(Unit) {
        onEvent(ChatEvent.GetChatList(startDate, page, size))
    }
    Log.d("ChatView", "execute")

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        reverseLayout = true
    ) {

    }
}

@Composable
fun ChatItem(chatData: ChatData) {
    // 닉네임
    // 메세지 박스
    // 시간
    // 메세지 꾹 누르면 신고 버튼 활성화

}

@Composable
fun MessageBox(message: String) {

}

