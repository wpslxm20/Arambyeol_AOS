package com.my_app.arambyeol.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.my_app.arambyeol.R

object TitleView {
    const val TITLE_FONT_SIZE = 17

    @Composable
    fun main() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.app_name_kor),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = TITLE_FONT_SIZE.sp
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            ) {
                val imagePainter = painterResource(id = R.drawable.ic_in_logo)
                Icon(
                    painter = imagePainter,
                    tint = Color.Unspecified, // 로고 색 표현
                    contentDescription = "아람별 로고"
                )
            }
        }
    }
}