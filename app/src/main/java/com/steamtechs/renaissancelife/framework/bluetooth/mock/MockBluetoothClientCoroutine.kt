package com.steamtechs.renaissancelife.framework.bluetooth.mock

import android.bluetooth.BluetoothDevice
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothMessageRequestModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.toRequestString
import kotlinx.coroutines.GlobalScope
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

        val messageRequestString = BluetoothMessageRequestModel(header, message).toRequestString()

        MockBluetoothHardware.write(device, messageRequestString)
    }
}