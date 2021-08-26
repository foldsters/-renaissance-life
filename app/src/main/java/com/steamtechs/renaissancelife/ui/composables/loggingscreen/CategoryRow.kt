package com.steamtechs.renaissancelife.ui.composables.loggingscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.steamtechs.renaissancelife.ui.composables.onLongPress

@Composable
fun CategoryRow(
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
            CategoryTextEditMode(categoryName, onNameChange, modifier.weight(2f), onEditState)
        } else {
            CategoryText(categoryName, modifier.weight(2f), onMenuState)
        }
        MinusButton(onTickChange, categoryTicks, modifier.weight(1f))
        TickText(categoryTicks, modifier.weight(1f))
        PlusButton(onTickChange, categoryTicks, modifier.weight(1f))
    }
}


@Composable
private fun PlusButton(
    onTickChange: (Int) -> Unit,
    categoryTicks: Int,
    modifier: Modifier
) {
    Button(
        onClick = { onTickChange(categoryTicks + 1) },
        modifier
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


@Composable
private fun TickText(categoryTicks: Int, modifier: Modifier) {
    Text(
        text = categoryTicks.toString(),
        textAlign = TextAlign.Center,
        fontSize = 10.em,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}


@Composable
private fun MinusButton(
    onTickChange: (Int) -> Unit,
    categoryTicks: Int,
    modifier: Modifier
) {
    Button(
        onClick = { onTickChange(categoryTicks - 1) },
        modifier
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
}


@Composable
private fun CategoryText(
    categoryName: String,
    modifier: Modifier,
    onMenuState: (Boolean) -> Unit
) {
    Text(
        text = categoryName,
        modifier = modifier.onLongPress { onMenuState(true) },
        textAlign = TextAlign.Center,
        fontSize = 6.em
    )
}


@Composable
private fun CategoryTextEditMode(
    categoryName: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier,
    onEditState: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = categoryName,
        onValueChange = onNameChange,
        modifier = modifier,
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onEditState(false)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    )
}