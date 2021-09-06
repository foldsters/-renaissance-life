package com.steamtechs.renaissancelife.framework.bluetooth.mocks

import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothServerController

class MockBluetoothServerController(private val messageCallback: (String, String?) -> Unit) :
    BluetoothServerController() {

    private var cancelled = false

    override fun run() {

        Log.i("server", "running")

        while (!cancelled) {

            val available = MockBluetoothHardware.available()

            when {
                available == -1 -> {
                    cancel()
                }
                available > 0   -> {
                    Log.i("server", "reading message")
                    val (device, message) = MockBluetoothHardware.read()
                    Log.i("server", "message: $message")
                    messageCallback(message, device?.address)
                }
            }

            sleep(10)

        }
    }

    override fun cancel() {
        cancelled = true
    }
}