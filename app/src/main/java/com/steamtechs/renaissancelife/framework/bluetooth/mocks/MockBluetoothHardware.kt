package com.steamtechs.renaissancelife.framework.bluetooth.mocks

import android.bluetooth.BluetoothDevice
import android.util.Log

object MockBluetoothHardware {

    private var messageData : Pair<BluetoothDevice?, String>? = null

    fun write(device : BluetoothDevice?, message : String) {
        messageData = Pair(device, message)
    }

    fun read() : Pair<BluetoothDevice?, String> {
        val result = messageData!!.copy()
        messageData = null
        return result
    }

    fun available() : Int {

        val available = messageData?.second?.length ?: 0
        Log.i("mock hardware", "availability check: $available")
        return available

    }

}