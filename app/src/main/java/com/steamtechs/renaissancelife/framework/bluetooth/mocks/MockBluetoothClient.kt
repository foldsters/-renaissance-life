package com.steamtechs.renaissancelife.framework.bluetooth.mocks


import android.bluetooth.BluetoothDevice
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothMessageRequestModel
import com.steamtechs.renaissancelife.framework.bluetooth.encodeBluetoothMessageRequestModel
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothClient

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