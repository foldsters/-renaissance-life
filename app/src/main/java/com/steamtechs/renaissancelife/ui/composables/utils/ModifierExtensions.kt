package com.steamtechs.renaissancelife.ui.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.onLongPress(longPress : (Offset) -> Unit) : Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onLongPress = longPress)
    }
}