package com.steamtechs.renaissancelife.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.steamtechs.renaissancelife.ui.AppViewModel
import com.steamtechs.renaissancelife.ui.LiveCategory


@Composable
fun Toggle() {

    val appViewModel = viewModel<AppViewModel>()
    val modifier = Modifier.fillMaxWidth()

    val liveCategories = appViewModel.liveCategoryList
    val liveCategoryState by liveCategories.observeAsState()

    println(">>> RECOMPOSING TOP LEVEL")

    val deleteCategory = {liveCategory : LiveCategory -> liveCategories.value = liveCategories.value?.minus(liveCategory)}

    CategoryColumn(liveCategoryState, deleteCategory, modifier)
}

@Composable
private fun CategoryColumn(
    liveCategoryState: List<LiveCategory>?,
    deleteCategory : (LiveCategory) -> Unit,
    modifier: Modifier
) {
    LazyColumn()
    {
        println(">> RECOMPOSING LAZY COLUMN")
        itemsIndexed(items = liveCategoryState!!, key = {_, lc -> lc.uniqueKey} ) { id, liveCategory ->


            val menuState: Boolean by liveCategory.menuState.observeAsState(true)
            val categoryName: String by liveCategory.title.observeAsState("New Category")
            val editState: Boolean by liveCategory.editState.observeAsState(false)
            val tickValue: Int by liveCategory.tickValue.observeAsState(0)

            val setName = liveCategory::setName
            val setTickValue = liveCategory::setTickValue
            val setMenuState = liveCategory::setMenuState
            val setEditState = liveCategory::setEditState


            println("Recomposing Row $id, ShowMenu : $menuState")

            Box(
                modifier = modifier,
                contentAlignment = Alignment.TopEnd
            )
            {
                ContextMenu(
                    menuItems = listOf("Edit", "Delete", "Dismiss"),
                    onClickCallbacks = listOf(
                        { setEditState(true) },
                        { deleteCategory(liveCategory) },
                        { }),
                    showMenu = menuState,
                    onDismiss = {
                        println("MENU STATE : false")
                        println("ID : $id")
                        setMenuState(false)
                    },
                    id = id
                )

                CategoryRow2(
                    categoryName = categoryName,
                    categoryTicks = tickValue,
                    onNameChange = { setName(it) },
                    onTickChange = { setTickValue(if (it >= 0) it else 0) },
                    onMenuState = {
                        println("MENU STATE: $it")
                        println("ID : $id")
                        setMenuState(it)
                    },
                    onEditState = { setEditState(it) },
                    modifier = modifier,
                    editMode = editState
                )
            }
        }
        item() {
            Box(modifier = Modifier.height(90.dp)) {

            }
        }


    }
}

