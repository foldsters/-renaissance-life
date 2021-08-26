package com.steamtechs.renaissancelife.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.steamtechs.renaissancelife.R
import com.steamtechs.renaissancelife.ui.AppViewModel
import com.steamtechs.renaissancelife.ui.LiveCategory


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

@Composable
fun FloatingActionButtonWithContext(
    fabIcon: Painter,
    menuItems: List<String>,
    toState: MultiFabState,
    stateChanged: (fabstate: MultiFabState) -> Unit,
    onMenuClickCallbacks : List<() -> Unit>
) {

    Column(horizontalAlignment = Alignment.End) {
        if (toState == MultiFabState.EXPANDED)
        {
            ContextMenu(menuItems = menuItems,
                onClickCallbacks = onMenuClickCallbacks,
                showMenu = true,
                onDismiss = { stateChanged(MultiFabState.COLLAPSED) })
        }
        FloatingActionButton(onClick = {
            println("INNER: $toState")
            stateChanged(
                if (toState == MultiFabState.EXPANDED) {
                    MultiFabState.COLLAPSED
                } else MultiFabState.EXPANDED
            )
        }) {
            Icon(
                painter = fabIcon,
                null,
                modifier = Modifier
            )
        }
    }
}

enum class MultiFabState {
    COLLAPSED, EXPANDED
}
