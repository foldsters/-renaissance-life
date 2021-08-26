package com.steamtechs.renaissancelife.ui.composables.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.steamtechs.renaissancelife.ui.composables.ContextMenu


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
