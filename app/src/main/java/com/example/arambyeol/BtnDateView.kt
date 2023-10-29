package com.example.arambyeol

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arambyeol.BtnDateView.FONT_SIZE



object BtnDateView {

    const val FONT_SIZE = 15

    @Composable
    fun main() {
        RadioGroup()
    }
}

@Composable
private fun RadioGroup() {
    val dateList = listOf(
        stringResource(id = R.string.today),
        stringResource(id = R.string.tomorrow),
        stringResource(id = R.string.day_after_tomorrow)
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf( dateList[0] ) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dateList.forEach { text ->
            RadioBtn(modifier =Modifier.weight(1f), text = text, selectedOption = selectedOption, onOptionSelected = onOptionSelected)
        }
    }

}

@Composable
private fun RadioBtn(modifier: Modifier, text: String, selectedOption: String, onOptionSelected: (text: String) -> Unit) {
    Column(
        modifier = modifier
            .selectable(
                selected = (text == selectedOption),
                onClick = {
                    onOptionSelected(text)
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

        if (selectedOption == text) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(color = colorResource(id = R.color.dark_yellow))
            )
        }
    }
}


