package com.steamtechs.renaissancelife.framework.bluetooth

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.ui.AppViewModel


@Composable
fun BluetoothExample() {

    val modifier = Modifier

    val appViewModel = viewModel<AppViewModel>()
    val bluetoothHandler = appViewModel.mockBluetoothHandlerImpl

    val deviceList = bluetoothHandler.devicesMap.values.toList()
    val deviceInfo = deviceList.map { "${it?.name ?: "Unknown"} \n ${it?.address}" }

    var message by remember { mutableStateOf("") }

    val onSetMessage : (String) -> Unit = { message = it }

    val sendDeviceCallbacks = deviceList.map { { bluetoothHandler.sendMessageToDevice(it, message, "Chat")  } }

    var showMenu by remember { mutableStateOf(false) }

    val receivedMessagesData : List<BluetoothMessageResponseModel> by appViewModel.receivedChatMessages.observeAsState(listOf())

    Log.i("Chat", receivedMessagesData.toString())

    val syncButtonCallback : () -> Unit = appViewModel::exampleRepositoryBluetoothSync


    Column{

        Row(horizontalArrangement = Arrangement.SpaceBetween) {

            TextField(message, onValueChange = { onSetMessage(it) }, modifier = modifier.weight(4f)) // DONE

            Box(modifier.weight(1f)) {

                if (showMenu)
                {
                    ContextMenu(
                        menuItems = deviceInfo,
                        onClickCallbacks = sendDeviceCallbacks,
                        showMenu = showMenu,
                        onDismiss = {showMenu = false
                                     onSetMessage("")}
                    )
                }

                Button(onClick = { showMenu = true }, modifier = modifier) {
                    Text("Send")
                }


            }
        }

        Button(onClick = syncButtonCallback) {
            Text(text="Sync")
        }


        for (receivedMessageData in receivedMessagesData) {
            Row {
                Column {
                    Text(receivedMessageData.header.toString(), fontSize = 3.em, color = Color(0xFFAAAAAA))
                    Text(receivedMessageData.deviceAddress ?: "UNKNOWN", fontSize = 3.em, color = Color(0xFFAAAAAA))
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(receivedMessageData.message, fontSize = 8.em)
            }
            Spacer(modifier = Modifier.height(5.dp))
        }

    }
}




@Composable
fun ContextMenu(
    menuItems: List<String>,
    onClickCallbacks: List<() -> Unit>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { onDismiss() }
    ) {
        menuItems.forEachIndexed { index, item ->
            DropdownMenuItem(onClick = {
                onClickCallbacks[index]()
                onDismiss()
            }) {
                Text(text = item)
            }
        }
    }
}
