package com.steamtechs.renaissancelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.ui.AppViewModel
import com.steamtechs.renaissancelife.ui.LiveCategory
import com.steamtechs.renaissancelife.ui.composables.Toggle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appViewModel.categoryList.add(LiveCategory.fromCategory(Category("Hello", "2021-08-13")))

        //setContent {HelloScreen(appViewModel)}
        setContent { Toggle() }
    }
}