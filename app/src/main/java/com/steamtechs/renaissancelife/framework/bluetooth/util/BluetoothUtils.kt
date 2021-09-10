package com.steamtechs.renaissancelife.framework.bluetooth.util

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothMessageRequestModel
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothMessageResponseModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

// App Identifier for Bluetooth
// Generated Randomly
val BluetoothUUID : UUID = UUID.fromString("fb5f7580-d51f-4e5d-b070-99edc1eaf1d4")


// Extension functions for structuring and destructuring the request and response models
fun BluetoothMessageRequestModel.Companion.fromRequestString(bluetoothMessageResponseString: String) : BluetoothMessageRequestModel =
    Json.decodeFromString(bluetoothMessageResponseString)

fun BluetoothMessageRequestModel.toRequestString(): String =
    Json.encodeToString(this)

fun BluetoothMessageResponseModel.Companion.fromRequestModel(messageRequestModel: BluetoothMessageRequestModel, device: BluetoothDevice?) : BluetoothMessageResponseModel {
    messageRequestModel.apply {
        return BluetoothMessageResponseModel(header, message, device?.name, device?.address)
    }
}


// Broadcast Receiver to run a callback whenever a new device is connected
fun bluetoothBroadcastReceiver(callback : (BluetoothDevice) -> Unit) = object : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND == action) {
            // Get the BluetoothDevice object from the Intent
            val device =
                intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    ?: return

            callback(device)
        }
    }
}

typealias BluetoothMessageCallback = (BluetoothMessageResponseModel) -> Unit

typealias Callback<K> = (K) -> Unit


