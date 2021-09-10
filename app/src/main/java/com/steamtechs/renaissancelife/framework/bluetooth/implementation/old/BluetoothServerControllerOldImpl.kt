package com.steamtechs.renaissancelife.framework.bluetooth.implementation.old

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothServerController
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothUUID
import java.io.IOException

open class BluetoothServerControllerOldImpl(private val messageCallback: (BluetoothMessageResponseModel) -> Unit) :
    BluetoothServerController, Thread() {

    private var cancelled: Boolean
    private val serverSocket: BluetoothServerSocket?

    private val tag = "server"

    // Create Server Socket
    init {
        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        if (btAdapter != null && btAdapter.isEnabled) {
            serverSocket = btAdapter.listenUsingRfcommWithServiceRecord("renlifeSync", BluetoothUUID)
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
                Log.i(tag,"ACCEPTING SERVER SOCKET")
                socket = serverSocket!!.accept()
                Log.i(tag,"ACCEPTED SERVER SOCKET")

            } catch(e: IOException) {
                break
            }

            // When the server socket is accepted,
            // Spawn a new server thread on the accepted socket
            if (!this.cancelled && socket != null) {
                Log.i(tag, "Connecting")
                BluetoothServerOldImpl(socket, messageCallback).start()
            }
        }
    }

    override fun cancel() {
        cancelled = true
        serverSocket!!.close()
    }
}