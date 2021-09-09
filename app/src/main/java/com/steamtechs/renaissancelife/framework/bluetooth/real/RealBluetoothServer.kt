package com.steamtechs.renaissancelife.framework.bluetooth.real

import android.bluetooth.BluetoothSocket
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.bluetooth.decodeBluetoothMessageRequestString
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothServer
import java.io.BufferedInputStream
import java.lang.Exception

class RealBluetoothServer(private val socket: BluetoothSocket,
                          val messageCallback : (BluetoothMessageResponseModel) -> Unit) : BluetoothServer() {

    private val inputStream = socket.inputStream
    private val outputStream = socket.outputStream

    private val bufferedInputStream = BufferedInputStream(inputStream)

    private val tag = "server"

    override fun run() {
        try {

            var available = 0
            var i = 0

            // Wait until a message is received
            while (available == 0) {
                available = bufferedInputStream.available()
                i++
            }

            println("Times the loop ran: $i")

            // Read input and convert to string
            val bytes = ByteArray(available)
            Log.i(tag, "Reading $available bytes")
            val bytesRead = bufferedInputStream.read(bytes, 0, available)
            val requestModelString = String(bytes)
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
}