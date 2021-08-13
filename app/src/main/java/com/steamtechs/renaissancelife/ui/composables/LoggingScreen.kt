package com.steamtechs.renaissancelife.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun LoggingScreen(){
    ConstraintLayout(modifier = Modifier.fillMaxSize())
    {
        Text("L")
        LazyColumn {
            for (i in 1..20) {
                item { CategoryRow(i.toString()) }
            }


        }
        
    }
}