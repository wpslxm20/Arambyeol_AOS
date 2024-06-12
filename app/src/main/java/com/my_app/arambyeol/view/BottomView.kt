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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my_app.arambyeol.R

object BottomView {
    private val FONT_SIZE = 11

    @Composable
    fun main() {
        Column {
            explanationText()
            Box(modifier = Modifier.height(10.dp))
        }

    }

    @Composable
    private fun explanationText() {
        val firstExplanation =
            stringResource(id = R.string.bottom_first_explanation)
        val middleExplanation = stringResource(id = R.string.bottom_middle_explanation)
        val finalExplanation = stringResource(id = R.string.bottom_final_explanation)


        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$firstExplanation",
                fontSize = FONT_SIZE.sp,
                color = colorResource(id = R.color.dark_gray),
                textAlign = TextAlign.Center
            )
            Row {
                Text(
                    text = "$middleExplanation",
                    fontSize = FONT_SIZE.sp,
                    color = colorResource(id = R.color.dark_gray),
                    textAlign = TextAlign.Center
                )
                SendEmailButton()
                Text(
                    text = "$finalExplanation",
                    fontSize = FONT_SIZE.sp,
                    color = colorResource(id = R.color.dark_gray),
                    textAlign = TextAlign.Center
                )
            }
        }

    }

    @Composable
    private fun SendEmailButton() {
        val emailAddr = stringResource(id = R.string.bottom_email_addr)

        val context = LocalContext.current
        Text(
            text = emailAddr,
            modifier = Modifier.clickable(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:$emailAddr")
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