package com.steamtechs.renaissancelife.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.steamtechs.renaissancelife.R

@Preview
@Composable
fun NavBar() {

    val items = listOf("Logging", "Radar")

    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color(0xFFF5F5F5),
        contentColor = Color(0xFF7300FF),
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(
                    painter = painterResource(id = R.drawable.ic_android_black_24dp),
                    "Protato"
                ) },
                label = { Text(screen) },
                selected = screen == "Radar",
                onClick = {}
            )
        }
    }
}