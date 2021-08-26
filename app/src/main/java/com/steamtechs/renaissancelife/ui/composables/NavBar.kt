package com.steamtechs.renaissancelife.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavBar(navTitles : List<String>,
           navIcons : List<Int>,
           navSelected : String,
           navOnSelect : (String) -> Unit,
           modifier : Modifier = Modifier) {

    BottomNavigation(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        backgroundColor = Color(0xFFF5F5F5),
        contentColor = Color(0xFF7300FF),
    ) {
        navTitles.forEachIndexed { index, title ->
            BottomNavigationItem(
                icon = { Icon (
                    painter = painterResource(navIcons[index]),
                    null ) },
                label = { Text(title) },
                selected = title == navSelected,
                onClick = { navOnSelect(title) }
            )
        }
    }
}