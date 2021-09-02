package com.steamtechs.renaissancelife.framework.bluetooth


import android.bluetooth.BluetoothDevice
import android.util.Log
import java.io.BufferedOutputStream
import java.lang.Exception

class BluetoothClient(device: BluetoothDevice, private val message : String): Thread() {

    private val socket = device.createRfcommSocketToServiceRecord(BluetoothConstants.uuid)

    override fun run() {
        Log.i("client", "Connecting")

        try {
            Log.i("client", "Socket Connected? ${socket.isConnected}")
            socket.connect()
        } catch(e : Exception) {
            Log.e("client", "Cannot Connect")
        }

        Log.i("client", "Sending: $message")
        val outputStream = socket.outputStream
        val inputStream = socket.inputStream

        val bufferedOutputStream = BufferedOutputStream(outputStream)

        try {
            bufferedOutputStream.write(message.toByteArray())
            bufferedOutputStream.flush()
            Log.i("client", "Sent")
        } catch(e: Exception) {
            Log.e("client", "Cannot send", e)
        } finally {
            outputStream.close()
            inputStream.close()
            socket.close()
        }
    }
}