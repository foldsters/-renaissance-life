package com.steamtechs.renaissancelife.framework.bluetooth.implementation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothServerController
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothServer
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothUUID
import kotlinx.coroutines.*
import java.io.IOException

open class BluetoothServerControllerImpl(private val messageCallback: (BluetoothMessageResponseModel) -> Unit) :
    BluetoothServerController {

    private val serverSocket: BluetoothServerSocket?

    private val tag = "server"

    private var job : Job? = null

    private var spawnedServers : MutableList<BluetoothServer> = mutableListOf()

    // Create Server Socket
    init {
        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        serverSocket = if (btAdapter != null && btAdapter.isEnabled) {
            btAdapter.listenUsingRfcommWithServiceRecord("renlifeSync", BluetoothUUID)
        } else {
            null
        }
    }


    override fun start() {
        job = CoroutineScope(Dispatchers.IO).launch { run() }
    }

    private suspend fun run() {
        var socket: BluetoothSocket

        // Keep looping until the server socket is accepted
        //  i.e. a client on another device has a message to send to this one
        while(true) {

            try {
                Log.i(tag,"ACCEPTING SERVER SOCKET")
                socket = serverSocket!!.accept()
                Log.i(tag,"ACCEPTED SERVER SOCKET")

            } catch(e: IOException) {
                break
            }

            // When the server socket is accepted,
            // Spawn a new server thread on the accepted socket
            if (socket != null) {
                Log.i(tag, "Connecting")
                val newServer = BluetoothServerImpl(socket, messageCallback)
                newServer.start()
                spawnedServers.add(newServer)
            }

            delay(10)
        }
    }

    override fun cancel() {
        spawnedServers.forEach { it.cancel() }
        job?.cancel()
        serverSocket!!.close()
    }
}