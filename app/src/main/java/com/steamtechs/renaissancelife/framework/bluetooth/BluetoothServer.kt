package com.steamtechs.renaissancelife.framework.bluetooth

import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.BufferedInputStream
import java.lang.Exception

class BluetoothServer(private val socket: BluetoothSocket,
                      val messageCallback : (String) -> Unit) : Thread() {

    private val inputStream = socket.inputStream
    private val outputStream = socket.outputStream

    private val bufferedInputStream = BufferedInputStream(inputStream)

    override fun run() {
        try {

            var available = 0
            var i = 0

            while (available == 0) {
                available = bufferedInputStream.available()
                i++
            }

            println("Times the loop ran: $i")

            val bytes = ByteArray(available)
            Log.i("server", "Reading $available bytes")
            val bytesRead = bufferedInputStream.read(bytes, 0, available)
            val text = String(bytes)
            Log.i("server", "Message received $bytesRead bytes")
            Log.i("server", "Message: $text")
            messageCallback(text)


        } catch (e: Exception) {
            Log.e("server", "Cannot read data", e)

        } finally {

            bufferedInputStream.close()
            outputStream.close()
            socket.close()

        }
    }
}