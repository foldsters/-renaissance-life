package com.steamtechs.renaissancelife

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {MyFirstComposable()}
    }
}
@Preview
@Composable
fun MyFirstComposable(){
    Box{
        Text(text = "Stuffs Here!")
    }
}