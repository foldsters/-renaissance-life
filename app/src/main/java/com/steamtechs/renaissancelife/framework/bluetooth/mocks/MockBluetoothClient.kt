package com.steamtechs.renaissancelife.framework.bluetooth.mocks


import android.bluetooth.BluetoothDevice
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothClient

class MockBluetoothClient(private val device: BluetoothDevice, private val message : String):
    BluetoothClient() {

    override fun run() {

        Log.i("client", "sending message: $message")

        MockBluetoothHardware.write(device, message)

    }
}