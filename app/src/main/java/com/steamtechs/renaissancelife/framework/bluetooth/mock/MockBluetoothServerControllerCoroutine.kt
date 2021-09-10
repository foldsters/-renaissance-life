package com.steamtechs.renaissancelife.framework.bluetooth.mock

import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothServerController
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.decodeBluetoothMessageRequestString
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MockBluetoothServerControllerCoroutine(private val messageCallback: (BluetoothMessageResponseModel) -> Unit) :
    BluetoothServerController {

    private var cancelled = false
    private var job : Job? = null

    override fun start() {
        job = GlobalScope.launch { run() }
    }

    private suspend fun run() {

        Log.i("server", "running")

        while (true) {

            val available = MockBluetoothHardware.available()

            when {
                available == -1 -> {
                    return
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

            delay(100)

        }
    }

    override fun cancel() {
        GlobalScope.launch { job?.cancel() }

    }
}