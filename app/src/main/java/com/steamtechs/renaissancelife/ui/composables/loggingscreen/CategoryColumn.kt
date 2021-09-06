package com.steamtechs.renaissancelife.ui.composables.loggingscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.steamtechs.renaissancelife.ui.LiveCategory
import com.steamtechs.renaissancelife.ui.composables.utils.ContextMenu


@Composable
fun CategoryColumn(
    liveCategoryState: List<LiveCategory>?,
    deleteCategory : (LiveCategory) -> Unit,
    modifier : Modifier
) {

    when {
        liveCategoryState == null -> {
            NoDataText(loading = true)
            return
        }
        liveCategoryState.isEmpty() -> {
            NoDataText(loading = false)
            return
        }
        else -> LazyColumn {

            itemsIndexed(
                items = liveCategoryState,
                key = { _, lc -> lc.uniqueKey }) { id, liveCategory ->

                val menuState: Boolean by liveCategory.menuState.observeAsState(false)
                val categoryName: String? by liveCategory.title.observeAsState()
                val editState: Boolean by liveCategory.editState.observeAsState(false)
                val tickValue: Int? by liveCategory.tickValue.observeAsState()

                val setName = liveCategory::setName
                val setTickValue = liveCategory::setTickValue
                val setMenuState = liveCategory::setMenuState
                val setEditState = liveCategory::setEditState

                println("Recomposing Row $id, ShowMenu : $menuState, Category Name : $categoryName")

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
                        }
                    )

                    CategoryRow(
                        categoryName = categoryName!!,
                        categoryTicks = tickValue!!,
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
            item {
                Box(modifier = Modifier.height(90.dp)) {
                }
            }
        }
    }

}

@Composable
fun NoDataText(loading : Boolean) {

    val text = if (loading) "Loading" else "No Data"

    println("LOADING:")
    println(text)

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(Modifier.height(32.dp))
        Text(
            text = text,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 48.sp,
            fontStyle = FontStyle.Italic,
            color = Color(0x44000000)
        )
    }
}

