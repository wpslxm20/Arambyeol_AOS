package com.my_app.arambyeol.chat.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.my_app.arambyeol.chat.viewmodel.ChatViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatView(
    navController: NavHostController,
    viewModel: ChatViewModel = hiltViewModel()
) {
//    val state = viewModel.chatListState
    val chatList = viewModel.chatList.collectAsLazyPagingItems()

//    LaunchedEffect(Unit) {
//        initializeChat(viewModel)
//    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(chatList.itemCount) { index ->
            var isFromUser by remember { mutableStateOf(false) }

            if (viewModel.shouldShowDateLabel(index, chatList)) {
                DateTextBox(date = viewModel.formatDateString(chatList[index]!!.sendTime))
            }
            LaunchedEffect(chatList[index]!!.senderDid) {
                isFromUser = viewModel.isMessageFromUser(chatList[index]!!.senderDid)
            }
            ChatItem(chatData = chatList[index]!!, isFromUser = isFromUser)
        }
    }
}

// 초기화 함수
@RequiresApi(Build.VERSION_CODES.O)
private fun initializeChat(viewModel: ChatViewModel) {
//    val state = viewModel.chatListState
//    viewModel.getChatList(state.startDate, state.page, state.size)
}

