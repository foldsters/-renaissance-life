package com.steamtechs.renaissancelife.framework.bluetooth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em


@Composable
fun BluetoothComposable() {

    val modifier = Modifier

    val deviceMap = BluetoothHandler.devicesMap
    val deviceInfo = deviceMap?.values?.toList()?.map { "${it.name ?: "Unknown"} \n ${it.address}" } ?: listOf()

    val message : String by BluetoothHandler.message.observeAsState("")
    val onSetMessage = BluetoothHandler::onSetMessage

    val sendDeviceCallbacks = BluetoothHandler.deviceCallbacks

    var showMenu by remember { mutableStateOf(false) }

    val receivedMessagesData : List<ReceivedMessageData> by BluetoothHandler.receivedMessagesData.observeAsState(listOf())
    val receivedMessages = receivedMessagesData.map {it.message}


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

        for (receivedMessage in receivedMessages) {
            Text(receivedMessage, fontSize = 8.em)
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
