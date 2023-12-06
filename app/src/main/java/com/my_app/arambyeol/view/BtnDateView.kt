package com.my_app.arambyeol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my_app.arambyeol.BtnDateView.FONT_SIZE
import com.my_app.arambyeol.data.DateEnum
import com.my_app.arambyeol.R


object BtnDateView {
    const val FONT_SIZE = 15
    @Composable
    fun main(selectedDay: MutableState<DateEnum>) {
        RadioGroup(selectedDay)
    }
}

@Composable
private fun RadioGroup(selectedDay: MutableState<DateEnum>) {
    val dateList = listOf(
        DateEnum.TODAY,
        DateEnum.TOMORROW,
        DateEnum.AFTER_TOMORROW
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dateList.forEach { text ->
            RadioBtn(
                modifier = Modifier.weight(1f),
                text = text.date, selectedOption = selectedDay.value,
                onOptionSelected = { selectedDay.value = it }
            )
        }
    }
}

@Composable
private fun RadioBtn(
    modifier: Modifier,
    text: String,
    selectedOption: DateEnum,
    onOptionSelected: (DateEnum) -> Unit
) {
    Column(
        modifier = modifier
            .selectable(
                selected = (text == selectedOption.date),
                onClick = {
                    onOptionSelected(DateEnum.values().first { it.date == text })
                },
                role = Role.RadioButton
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp),
            text = text,
            color = Color.Black,
            fontSize = FONT_SIZE.sp,
        )

        if (selectedOption.date == text) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(color = colorResource(id = R.color.dark_yellow))
            )
        }
    }
}

