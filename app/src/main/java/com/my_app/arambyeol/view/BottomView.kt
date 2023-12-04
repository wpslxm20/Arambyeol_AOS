package com.my_app.arambyeol.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my_app.arambyeol.R

object BottomView {
    private const val FIRST_EXPLANATION =
        "매주 월요일 1시에 식단표가 업데이트 됩니다!"
    private const val MIDDLE_EXPLANATION = "앱 아람별 문의사항은 "
    private const val FINAL_EXPLANATION = " 로 보내주세요 :)"
    private const val EMAILADDR = "gogosusumlnmln@gmail.com"
    private const val FONT_SIZE = 11

    @Composable
    fun main() {
        Column {
            explanationText()
            Box(modifier = Modifier.height(10.dp))
        }

    }

    @Composable
    private fun explanationText() {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$FIRST_EXPLANATION",
                fontSize = FONT_SIZE.sp,
                color = colorResource(id = R.color.dark_gray),
                textAlign = TextAlign.Center
            )
            Row {
                Text(
                    text = "$MIDDLE_EXPLANATION",
                    fontSize = FONT_SIZE.sp,
                    color = colorResource(id = R.color.dark_gray),
                    textAlign = TextAlign.Center
                )
                SendEmailButton()
                Text(
                    text = "$FINAL_EXPLANATION",
                    fontSize = FONT_SIZE.sp,
                    color = colorResource(id = R.color.dark_gray),
                    textAlign = TextAlign.Center
                )
            }
        }

    }

    @Composable
    private fun SendEmailButton() {
        val context = LocalContext.current
        Text(
            text = EMAILADDR,
            modifier = Modifier.clickable(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:$EMAILADDR")
                    }

                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
            ),
            color = colorResource(id = R.color.blue),
            fontSize = FONT_SIZE.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.None
        )
    }

}