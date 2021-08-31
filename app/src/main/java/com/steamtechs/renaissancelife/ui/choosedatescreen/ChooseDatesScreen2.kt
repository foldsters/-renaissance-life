package com.steamtechs.renaissancelife.ui.choosedatescreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun ChooseDatesScreen2(){

    val modifier = Modifier

    Column() {
        Text(text = "Choose Dates!")
        Text(text = "Start: ", modifier = modifier.clickable {  })
        Box(modifier = modifier.clickable { TODO() }
        ){
            Text(text = "StartDate", fontSize = 12.em)}
    }
}