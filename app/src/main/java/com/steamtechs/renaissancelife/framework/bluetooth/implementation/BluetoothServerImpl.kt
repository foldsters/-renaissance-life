package com.steamtechs.renaissancelife.framework.bluetooth.implementation

import android.bluetooth.BluetoothSocket
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothServer
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.decodeBluetoothMessageRequestString
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.lang.Exception

class BluetoothServerImpl(private val socket: BluetoothSocket,
                          val messageCallback : (BluetoothMessageResponseModel) -> Unit) : BluetoothServer {

    private val inputStream = socket.inputStream
    private val outputStream = socket.outputStream

    private val bufferedInputStream = BufferedInputStream(inputStream)

    private val tag = "server"

    private var job : Job? = null

    override fun start() {
        job = CoroutineScope(Dispatchers.IO).launch { run() }
    }

    private suspend fun run() {
        try {

            var requestModelString = ""

            var available = 0
            var i = 0

            // Wait until a message is received
            while (available == 0) {
                available = bufferedInputStream.available()
                i++
                delay(10)
            }

            println("Times the loop ran: $i")

            // Read input and convert to string
            var bytes = ByteArray(available)
            Log.i(tag, "Reading $available bytes")
            var bytesRead = bufferedInputStream.read(bytes, 0, available)
            requestModelString += String(bytes)

            delay(10)

            // Ensure that the buffer is empty

            available = bufferedInputStream.available()
            i = 0
            while (available != 0) {

                bytes = ByteArray(available)
                Log.i(tag, "Reading $available additional bytes")
                bytesRead = bufferedInputStream.read(bytes, 0, available)
                requestModelString += String(bytes)

                delay(10)

                available = bufferedInputStream.available()
                i++

                if (i > 1000) {
                    throw Exception("Buffer never cleared")
                }
            }


            Log.i(tag, "Message received $bytesRead bytes")
            Log.i(tag, "Message: $requestModelString")

            val device = socket.remoteDevice

            val requestModel = decodeBluetoothMessageRequestString(requestModelString)
            val responseModel = BluetoothMessageResponseModel.fromRequestModel(requestModel, device)

            messageCallback(responseModel)


        } catch (e: Exception) {
            Log.e(tag, "Cannot read data", e)

        } finally {

            bufferedInputStream.close()
            outputStream.close()
            socket.close()

        }
    }

    override fun cancel() {
        GlobalScope.launch { job?.cancel() }
    }
}