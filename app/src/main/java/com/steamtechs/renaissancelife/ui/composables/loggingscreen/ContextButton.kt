package com.steamtechs.renaissancelife.ui.composables.loggingscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.steamtechs.renaissancelife.R
import com.steamtechs.renaissancelife.ui.composables.utils.FloatingActionButtonWithContext
import com.steamtechs.renaissancelife.ui.composables.utils.MultiFabState


@Composable
fun ContextButton(addCategoryCallback : () -> Unit,
                  saveCategoryCallback : () -> Unit) {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    Box(contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 20.dp, 20.dp, 20.dp))
    {
        FloatingActionButtonWithContext(
            fabIcon = painterResource(id = R.drawable.ic_menu),
            menuItems = listOf("Add New Category", "Save The Day"),
            toState = toState,
            stateChanged = {
                println("BUTTON CLICKED $it")
                toState = it
            },
            onMenuClickCallbacks = listOf(addCategoryCallback, saveCategoryCallback)
        )
    }
}
