package com.steamtechs.renaissancelife.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.steamtechs.renaissancelife.ui.FakeCategoryRow

@Composable
fun PopupMenu(
    menuItems: List<String>,
    onClickCallbacks: List<() -> Unit>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
    modifier : Modifier = Modifier
) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { onDismiss() }
    ) {
        menuItems.forEachIndexed { index, item ->
            DropdownMenuItem(onClick = {
                onClickCallbacks[index]()
                onDismiss()
            }) {
                Text(text = item)
            }
        }
    }
}

@Composable
fun Toggle() {

    var showMenu1 by remember { mutableStateOf(false) }
    var categoryName1 by remember { mutableStateOf("Hello") }
    var editMode1 by remember { mutableStateOf(false) }
    var tickValue1 by remember { mutableStateOf(0) }

    var showMenu2 by remember { mutableStateOf(false) }
    var categoryName2 by remember { mutableStateOf("Hello") }
    var editMode2 by remember { mutableStateOf(false) }
    var tickValue2 by remember { mutableStateOf(0) }

    val modifier = Modifier.fillMaxWidth()

    Column()
    {
        Box(modifier = modifier,
            contentAlignment = Alignment.TopEnd
        )
        {
            PopupMenu(
                menuItems = listOf("Edit", "Dismiss"),
                onClickCallbacks = listOf(
                    { editMode1 = true },
                    {  }),
                showMenu = showMenu1,
                onDismiss = { showMenu1 = false }
            )

            FakeCategoryRow (
                categoryName = categoryName1,
                categoryTicks = tickValue1,
                onNameChange = { categoryName1 = it },
                onTickChange = {tickValue1 = it},
                onMenuState = { showMenu1 = it },
                onEditState = { editMode1 = it },
                modifier = modifier,
                editMode = editMode1
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        )
        {
            PopupMenu(
                menuItems = listOf("Edit", "Dismiss"),
                onClickCallbacks = listOf(
                    { editMode2 = true },
                    {  }),
                showMenu = showMenu2,
                onDismiss = { showMenu2 = false }
            )

            FakeCategoryRow(
                categoryName = categoryName2,
                categoryTicks = tickValue2,
                onNameChange = { categoryName2 = it },
                onTickChange = { tickValue2 = it },
                onMenuState = { showMenu2 = it },
                onEditState = { editMode2 = it },
                modifier = modifier,
                editMode = editMode2
            )
        }
    }
}

