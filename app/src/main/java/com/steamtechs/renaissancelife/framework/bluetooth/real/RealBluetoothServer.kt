package com.steamtechs.renaissancelife.framework.bluetooth.real

import android.bluetooth.BluetoothSocket
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothServer
import java.io.BufferedInputStream
import java.lang.Exception

class RealBluetoothServer(private val socket: BluetoothSocket,
                          val messageCallback : (String, String?) -> Unit) : BluetoothServer() {

    private val inputStream = socket.inputStream
    private val outputStream = socket.outputStream

    private val bufferedInputStream = BufferedInputStream(inputStream)

    private val deviceAddress : String? = socket.remoteDevice.address

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
            Log.i("server", "Reading $available bytes")
            val bytesRead = bufferedInputStream.read(bytes, 0, available)
            val text = String(bytes)
            Log.i("server", "Message received $bytesRead bytes")
            Log.i("server", "Message: $text")

            // Call the callback with the received message
            messageCallback(text, deviceAddress)


        } catch (e: Exception) {
            Log.e("server", "Cannot read data", e)

        } finally {

            bufferedInputStream.close()
            outputStream.close()
            socket.close()

        }
    }
}