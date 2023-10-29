package com.example.arambyeol

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TitleView {

    const val TITLE_FONT_SIZE = 17

    @Composable
    fun main() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 빈 레이아웃
            Box(modifier = Modifier.weight(1f))

            //Title
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.app_name_kor),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = TITLE_FONT_SIZE.sp
            )

            // BtnBottomSheetShow
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            ) {
                ButtonShowTimeBottomSheet(
                    onClick = { /*TODO*/ }) {
                }
            }

        }
    }

    // 이미지 버튼
    // content : IconButton에 그려질 Icon을 포함
    @Composable
    fun ButtonShowTimeBottomSheet(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            val imagePainter = painterResource(id = R.drawable.ic_btn_show_time)
            Icon(
                painter = imagePainter,
                tint = Color.Unspecified, // 로고 색 표현
                contentDescription = "아람별 로고"
            )
        }
    }
}