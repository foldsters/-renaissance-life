package com.steamtechs.renaissancelife.framework.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException

class BluetoothServerController(private val messageCallback: (String) -> Unit) : Thread() {

    private var cancelled: Boolean
    private val serverSocket: BluetoothServerSocket?

    init {
        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        if (btAdapter != null && btAdapter.isEnabled) {
            serverSocket = btAdapter.listenUsingRfcommWithServiceRecord("test", BluetoothConstants.uuid)
            cancelled = false
        } else {
            serverSocket = null
            cancelled = true
        }
    }

    override fun run() {
        var socket: BluetoothSocket

        while(true) {
            if (cancelled) {
                break
            }

            try {
                Log.i("server","ACCEPTING SERVER SOCKET")
                socket = serverSocket!!.accept()
                Log.i("server","ACCEPTED SERVER SOCKET")

            } catch(e: IOException) {
                break
            }

            if (!this.cancelled && socket != null) {
                Log.i("server", "Connecting")
                BluetoothServer(socket, messageCallback).start()
            }
        }
    }

    fun cancel() {
        cancelled = true
        serverSocket!!.close()
    }
}