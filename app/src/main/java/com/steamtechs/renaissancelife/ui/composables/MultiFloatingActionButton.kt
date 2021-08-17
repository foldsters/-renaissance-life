package com.steamtechs.renaissancelife.ui.composables

import android.transition.Transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.steamtechs.renaissancelife.R
import com.steamtechs.renaissancelife.ui.AppViewModel


@Composable
fun MainPreview() {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    val appViewModel = viewModel<AppViewModel>()

    Box(contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 20.dp, 20.dp, 20.dp))
    {
        MultiFloatingActionButton(
            fabIcon = painterResource(id = R.drawable.ic_menu),
            menuItems = listOf("Add New Category", "Save The Day"),
            toState = toState,
            stateChanged = {
                println("BUTTON CLICKED $it")
                toState = it
            },
            onMenuClickCallbacks = listOf({ appViewModel.addNewLiveCategory() },
                                          { appViewModel.saveLiveCategories() })
        )
    }
}

@Composable
fun MultiFloatingActionButton(
    fabIcon: Painter,
    menuItems: List<String>,
    toState: MultiFabState,
    stateChanged: (fabstate: MultiFabState) -> Unit,
    onMenuClickCallbacks : List<() -> Unit>
) {

    Column(horizontalAlignment = Alignment.End) {
        if (toState == MultiFabState.EXPANDED)
        {
            ContextMenu(menuItems = menuItems,
                onClickCallbacks = onMenuClickCallbacks,
                showMenu = true,
                onDismiss = { stateChanged(MultiFabState.COLLAPSED) })
        }
        FloatingActionButton(onClick = {
            println("INNER: $toState")
            stateChanged(
                if (toState == MultiFabState.EXPANDED) {
                    MultiFabState.COLLAPSED
                } else MultiFabState.EXPANDED
            )
        }) {
            Icon(
                painter = fabIcon,
                null,
                modifier = Modifier
            )
        }
    }
}


enum class MultiFabState {
    COLLAPSED, EXPANDED
}





//@Composable
//private fun MiniFabItem(
//    item: MultiFabItem,
//    showLabel: Boolean,
//    onFabItemClicked: (item: MultiFabItem) -> Unit
//) {
//    val fabColor = MaterialTheme.colors.secondary
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//    ) {
//        if (showLabel) {
//            Text(
//                item.label,
//                fontSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .background(color = MaterialTheme.colors.surface)
//                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//        }
//        Box(contentAlignment = Alignment.Center) {
//            Canvas(
//                modifier = Modifier
//                    .size(32.dp)
//                    .clickable(
//                        onClick = { onFabItemClicked(item) }
//                    )
//            ) {
//                drawCircle(color = fabColor, 56f)
//            }
//            Image(painter = item.icon, contentDescription = null, modifier = Modifier.size(56.dp))
//        }
//    }
//}
//
//class MultiFabItem(
//    val identifier: String,
//    val icon: Painter,
//    val label: String
//)