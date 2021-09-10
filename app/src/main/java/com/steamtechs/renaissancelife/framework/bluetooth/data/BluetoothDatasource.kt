package com.steamtechs.renaissancelife.framework.bluetooth.data

import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageCallback

interface BluetoothDatasource {

    // Public device map from address to device, getter gets all the paired devices
    fun getDevices() : HashMap<String, String>

    // Sends message to the device's server
    fun sendMessageToDevice(deviceAddress: String?, message: String, header: String? = null)

    // Starts the bluetooth server controller
    // This is to be called from the activity onResume
    fun startBluetoothServer()

    // Stops the bluetooth server
    // This is to be called from the activity onPause
    fun stopBluetoothServer()

    // Registers a function to be called when the server receives a message with that header
    fun registerBluetoothMessageResponseCallback(
        header: String,
        callback: BluetoothMessageCallback
    ): () -> Unit
}