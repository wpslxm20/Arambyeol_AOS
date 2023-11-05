package com.example.arambyeol

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

object TitleView {

    const val TITLE_FONT_SIZE = 17

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun TitleView() {
        val navController = rememberNavController()
        val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val coroutineScope = rememberCoroutineScope()

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                Text("This is a bottom sheet content")
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) // 빈 레이아웃

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
                    IconButton(onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    }) {
                        val imagePainter = painterResource(id = R.drawable.ic_btn_show_time)
                        Icon(
                            painter = imagePainter,
                            tint = Color.Unspecified, // 로고 색 표현
                            contentDescription = "아람별 로고"
                        )
                    }
                }
            }
        }
    }

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
                ButtonShowTimeBottomSheet(
                    onClick = { /*TODO*/ }) {
                }
            }
        }
    }

    @Composable
    fun ButtonShowTimeBottomSheet(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        IconButton(onClick = onClick) {
            val imagePainter = painterResource(id = R.drawable.ic_btn_show_time)
            Icon(
                painter = imagePainter,
                tint = Color.Unspecified, // 로고 색 표현
                contentDescription = "아람별 로고"
            )
        }
    }

//    @OptIn(ExperimentalMaterialApi::class)
//    @Composable
//    fun BottomSheetWithNavController() {
//        val navController = rememberNavController()
//        val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//        val coroutineScope = rememberCoroutineScope()
//
//        ModalBottomSheetLayout(
//            sheetState = bottomSheetState,
//            sheetContent = {
//                Text("This is a bottom sheet content")
//            }
//        ) {
//            NavHost(navController, startDestination = "main_screen") {
//                composable("main_screen") {
//                    Button(onClick = {
//                        coroutineScope.launch {
//                            bottomSheetState.show()
//                        }
//                    }) {
//                        Text("Show Bottom Sheet")
//                    }
//                }
//            }
//        }
//    }
//    @OptIn(ExperimentalMaterialApi::class)
//    @Composable
//    fun TimeBottomSheet() {
//        val scope = rememberCoroutineScope()
//        val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
//
//        ModalBottomSheetLayout(
//            sheetState = sheetState,
//            sheetContent = {
//                Text("hi")
//            }
//        ) {
//            ButtonShowTimeBottomSheet(onClick = {
//                scope.launch {
//                    sheetState.show()
//                }
//            }){
//
//            }
//        }
//    }
}