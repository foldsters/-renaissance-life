package com.steamtechs.renaissancelife.framework.bluetooth.core

import android.bluetooth.BluetoothDevice
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageCallback

interface BluetoothHandler {

    // Public device map, getter gets all the paired devices
    val devicesMap: HashMap<String, BluetoothDevice?>

    // Sends message to the device's server
    fun sendMessageToDevice(device: BluetoothDevice?, message: String, header: String? = null)

    // Starts the bluetooth server controller
    // This is to be called from the activity onResume
    fun startBluetoothServerController()

    // Stops the bluetooth server
    // This is to be called from the activity onPause
    fun stopBluetoothServer()

    // Registers a function to be called when the server receives a message with that header
    fun registerBluetoothMessageResponseCallback(
        header: String,
        callback: BluetoothMessageCallback
    ): () -> Unit
}