package com.steamtechs.renaissancelife.framework.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException

class BluetoothServerController(private val messageCallback: (String, String?) -> Unit) : Thread() {

    private var cancelled: Boolean
    private val serverSocket: BluetoothServerSocket?

    // Create Server Socket
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

        // Keep looping until the server socket is accepted
        //  i.e. a client on another device has a message to send to this one
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

            // When the server socket is accepted,
            // Spawn a new server thread on the accepted socket
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