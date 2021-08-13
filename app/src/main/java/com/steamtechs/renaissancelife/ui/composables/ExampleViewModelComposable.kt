package com.steamtechs.renaissancelife.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun FakeCategoryRow(
    categoryName: String,
    categoryTicks: Int,
    onNameChange: (String) -> Unit,
    onTickChange: (Int) -> Unit,
    onEditState : (Boolean) -> Unit,
    onMenuState : (Boolean) -> Unit,
    modifier : Modifier = Modifier,
    editMode: Boolean = false,
) {

    Row(
        modifier = modifier.height(75.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (editMode) {
            val focusManager = LocalFocusManager.current
            TextField (
                value = categoryName,
                onValueChange = onNameChange,
                modifier = modifier.weight(2f),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    onEditState(false)
                }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
        } else {
            Text (
                categoryName,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .weight(2f)
                    .onLongPress { onMenuState(true) }
            )
        }
        Button(
            onClick = {onTickChange(categoryTicks - 1)},
            modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(5.dp)
        ) {
            Text(
                text = "-",
                fontSize = 8.em,
                fontWeight = FontWeight.ExtraBold,
                modifier = modifier,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = categoryTicks.toString(),
            textAlign = TextAlign.Center,
            fontSize = 10.em,
            fontWeight = FontWeight.Bold,
            modifier = modifier.weight(1f)
        )
        Button(
            onClick = {onTickChange(categoryTicks + 1)},
            modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(5.dp)
        ) {
            Text(
                text = "+",
                fontSize = 8.em,
                textAlign = TextAlign.Center
            )

        }
    }
}

fun Modifier.onLongPress(longPress : (Offset) -> Unit) : Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onLongPress = longPress)
    }
}