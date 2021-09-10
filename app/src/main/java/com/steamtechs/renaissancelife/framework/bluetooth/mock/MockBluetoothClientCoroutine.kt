package com.steamtechs.renaissancelife.framework.bluetooth.mock

import android.bluetooth.BluetoothDevice
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageRequestModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.encodeBluetoothMessageRequestModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MockBluetoothClientCoroutine(
    private val device: BluetoothDevice?,
    private val message: String,
    private val header: String? = null) : BluetoothClient {

    override fun start() {
        GlobalScope.launch { send() }
    }

    private fun send() {
        Log.i("client", "sending message: $message")

        val messageRequestModel = BluetoothMessageRequestModel(header, message)
        val messageRequestString = encodeBluetoothMessageRequestModel(messageRequestModel)

        MockBluetoothHardware.write(device, messageRequestString)
    }
}