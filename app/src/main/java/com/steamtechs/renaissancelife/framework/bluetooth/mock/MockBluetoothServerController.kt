package com.steamtechs.renaissancelife.framework.bluetooth.mock

import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothServerController
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.decodeBluetoothMessageRequestString

class MockBluetoothServerController(private val messageCallback: (BluetoothMessageResponseModel) -> Unit) :
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

                    val requestModel = decodeBluetoothMessageRequestString(message)
                    val responseModel = BluetoothMessageResponseModel.fromRequestModel(requestModel, device)

                    messageCallback(responseModel)
                }
            }

            sleep(100)

        }
    }

    override fun cancel() {
        cancelled = true
    }
}