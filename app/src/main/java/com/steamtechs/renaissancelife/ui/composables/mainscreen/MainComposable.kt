package com.steamtechs.renaissancelife.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.steamtechs.renaissancelife.ui.AppViewModel
import com.steamtechs.renaissancelife.ui.LiveCategory
import com.steamtechs.renaissancelife.ui.NavBar

@Preview
@Composable
@Preview
fun MainComposable() {

    val appViewModel = viewModel<AppViewModel>()

    // Callbacks
    val addCategory = appViewModel::addNewLiveCategory
    val deleteCategory = appViewModel::deleteLiveCategory
    val saveCategories = appViewModel::saveLiveCategories

    // Navigation
    val navTitles = appViewModel.navTitles
    val navIcons = appViewModel.navIcons
    val navSelected : String by appViewModel.navSelected.observeAsState( navTitles[0] )
    val navOnSelect = appViewModel::navOnSelect

    // Logging
    val liveCategoryList : List<LiveCategory>? by appViewModel.liveCategoryList.observeAsState()

    // Radar
    val summary by viewModel<AppViewModel>().radarSummaryLists.observeAsState()


    val modifier = Modifier

    Column(modifier = Modifier) {

        Box(modifier.weight(1f)) {

            when (navSelected) {

                "Logging" -> {
                    CategoryColumn(
                        liveCategoryList,
                        deleteCategory,
                        modifier
                    )
                    ContextButton(
                        addCategory,
                        saveCategories
                    )
                }

                "Radar"   -> summary?.let { Radar(it) }
            }
        }

        NavBar (
            navTitles,
            navIcons,
            navSelected,
            navOnSelect,
            modifier.weight(1f))
    }
}