package com.steamtechs.renaissancelife.framework.bluetooth.mock


import android.bluetooth.BluetoothDevice
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageRequestModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.encodeBluetoothMessageRequestModel

class MockBluetoothClient(
    private val device: BluetoothDevice?,
    private val message: String,
    private val header: String? = null
): BluetoothClient() {

    override fun run() {

        Log.i("client", "sending message: $message")

        val messageRequestModel = BluetoothMessageRequestModel(header, message)
        val messageRequestString = encodeBluetoothMessageRequestModel(messageRequestModel)

        MockBluetoothHardware.write(device, messageRequestString)

    }
}