package com.steamtechs.renaissancelife.framework.bluetooth.implementation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.util.Log
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothServerController
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageCallback
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageResponseModel

class BluetoothHandlerImpl(private val bluetoothServerControllerConstructor : (BluetoothMessageCallback) -> BluetoothServerController,
                           private val bluetoothClientConstructor : (BluetoothDevice?, String, String?) -> BluetoothClient) :
    BluetoothHandler {


    // Map of all the devices this devices is paired with
    private var _devicesMap = hashMapOf<String, BluetoothDevice?>("this" to null)
    private var server : BluetoothServerController? = null

    // A server instance


    val tag : String = "BTHandler"

    // Public device map, address to device name
    override val devicesMap : HashMap<String, String>
        get() {
            val btAdapter = BluetoothAdapter.getDefaultAdapter()

            if (btAdapter == null || !btAdapter.isEnabled) {

                println("BLUETOOTH IS DISABLED")
                return _devicesMap.mapValues { (_, it) -> it?.name ?: "Unknown"} as HashMap<String, String>

            }

            for (device in BluetoothAdapter.getDefaultAdapter().bondedDevices) {
                _devicesMap[device.address] = device
            }

            return _devicesMap.mapValues { (_, it) -> it?.name ?: "Unknown"} as HashMap<String, String>
        }



    // Makes a client connection and sends message to the device's server
    override fun sendMessageToDevice(deviceAddress : String?, message : String, header : String?) {

        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
        bluetoothClientConstructor(_devicesMap[deviceAddress], message, header).start()

    }

    // Starts the internal bluetooth server controller
    // This is to be called from the activity onResume
    override fun startBluetoothServerController() {
        if (server == null) {
            server = bluetoothServerControllerConstructor(::innerMessageReceiveCallback).apply { start() }
            server
        }
    }

    // Stops the bluetooth server
    // This is to be called from the activity onPause
    override fun stopBluetoothServer() {
        server?.cancel()
        server = null
    }

    private val bluetoothCallbackRoster : MutableMap<String, MutableList<BluetoothMessageCallback>> = mutableMapOf()

    override fun registerBluetoothMessageResponseCallback(header : String, callback : BluetoothMessageCallback) : () -> Unit {

        Log.i(tag, "Registering Callback")

        bluetoothCallbackRoster.putIfAbsent(header, mutableListOf())
        bluetoothCallbackRoster[header]!!.add(callback)

        return { bluetoothCallbackRoster[header]?.remove(callback) } // Unregister callback
    }

    // Used as a callback for the server
    // When the server receives a message, it adds it to the receivedMessages list
    private fun innerMessageReceiveCallback(btResponseModel: BluetoothMessageResponseModel) {
        println("CALLBACK CALLED $btResponseModel")

        // Update callback roster
        bluetoothCallbackRoster[btResponseModel.header]?.forEach { it(btResponseModel) }

    }
}