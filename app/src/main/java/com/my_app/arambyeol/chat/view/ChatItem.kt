package com.my_app.arambyeol.chat.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my_app.arambyeol.chat.data.remote.model.ChatData
import com.my_app.arambyeol.ui.theme.GrayColor02
import com.my_app.arambyeol.ui.theme.GrayColor04
import com.my_app.arambyeol.ui.theme.GrayColor05
import com.my_app.arambyeol.ui.theme.LightBlue
import com.my_app.arambyeol.ui.theme.LightYellow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatItem(chatData: ChatData, isFromUser: Boolean) {
    val alignment = if (isFromUser) Alignment.End else Alignment.Start
    val backgroundColor = if (isFromUser) LightYellow else LightBlue
    val shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = if (isFromUser) 0.dp else 16.dp,
        bottomStart = if (isFromUser) 16.dp else 0.dp
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp, vertical = 8.dp),
        horizontalAlignment = alignment
    ) {
        ChatNicknameText(chatData.senderNickname)
        Row(
            horizontalArrangement = if (isFromUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            if (isFromUser) SendTimeText(time = chatData.sendTime, Modifier.padding(end = 8.dp, bottom = 3.dp))
            MessageBox(
                message = chatData.message,
                backgroundColor = backgroundColor,
                shape = shape
            )
            if (!isFromUser) SendTimeText(time = chatData.sendTime, Modifier.padding(start = 8.dp, bottom = 3.dp))
        }
    }
}

@Composable
fun ChatNicknameText(nickname: String) {
    Text(
        modifier = Modifier.padding(bottom = 5.dp),
        text = nickname,
        color = GrayColor05,
        fontSize = 12.sp
    )
}

@Composable
fun MessageBox(message: String, backgroundColor: Color, shape: RoundedCornerShape) {
    Box(
        modifier = Modifier
            .background(backgroundColor, shape)
            .padding(vertical = 8.dp, horizontal = 10.dp),
    ) {
        Text(
            text = message,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SendTimeText(time: String, modifier: Modifier = Modifier) {
    val dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
    val formattedTime = dateTime.format(formatter)

    Text(
        modifier = modifier,
        text = formattedTime,
        fontSize = 10.sp,
        color = GrayColor04
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTextBox(date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(GrayColor02, RoundedCornerShape(10.dp))
                .width(130.dp)
                .height(22.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "- $date -",
                color = GrayColor05,
                fontSize = 10.sp
            )
        }
    }
}