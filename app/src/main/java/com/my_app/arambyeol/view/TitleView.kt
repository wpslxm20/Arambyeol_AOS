package com.my_app.arambyeol.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.my_app.arambyeol.R
import com.my_app.arambyeol.nav.NavItem

object TitleView {
    const val TITLE_FONT_SIZE = 17

    @Composable
    fun main(navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight())
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .fillMaxHeight(),
                text = stringResource(id = R.string.app_name_kor),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = TITLE_FONT_SIZE.sp
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chat),
                        tint = Color.Unspecified, // 로고 색 표현
                        contentDescription = "아람별 로고",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.navigate(NavItem.Chat.route) },

                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_in_logo),
                        tint = Color.Unspecified, // 로고 색 표현
                        contentDescription = "아람별 로고",
                        modifier = Modifier.size(27.dp)
                    )
                }
            }
        }
    }
}