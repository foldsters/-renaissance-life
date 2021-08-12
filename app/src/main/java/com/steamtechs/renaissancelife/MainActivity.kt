package com.steamtechs.renaissancelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import com.steamtechs.renaissancelife.framework.db.CategoryDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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