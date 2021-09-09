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

    // Public device map, getter gets all the paired devices
    override val devicesMap : HashMap<String, BluetoothDevice?>
        get() {
            val btAdapter = BluetoothAdapter.getDefaultAdapter()

            if (btAdapter == null || !btAdapter.isEnabled) {

                println("BLUETOOTH IS DISABLED")
                return _devicesMap

            }

            for (device in BluetoothAdapter.getDefaultAdapter().bondedDevices) {
                _devicesMap[device.address] = device
            }

            return _devicesMap
        }

//    // Broadcast Receiver to add newly paired devices to the device map
//    var bluetoothBroadcastReceiver = object : BroadcastReceiver() {
//
//        override fun onReceive(context: Context, intent: Intent) {
//            val action = intent.action
//            // When discovery finds a device
//            if (BluetoothDevice.ACTION_FOUND == action) {
//                // Get the BluetoothDevice object from the Intent
//                val device =
//                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
//                        ?: return
//
//                _devicesMap[device.address] = device
//            }
//        }
//    }

    // Makes a client connection and sends message to the device's server
    override fun sendMessageToDevice(device : BluetoothDevice?, message : String, header : String?) {

        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
        bluetoothClientConstructor(device, message, header).start()

    }

    // Makes a list of sendMessage functions, one for each paired device.
    val deviceCallbacks : List<(String, String?) -> Unit>
        get() {
            return _devicesMap.values.toList().map { {message, header -> sendMessageToDevice(it, message, header) } }
        }

    // Starts the internal bluetooth server controller
    // This is to be called from the activity onResume
    override fun startBluetoothServerController() {
        if (server == null) {
            server = bluetoothServerControllerConstructor(::innerMessageReceiveCallback).apply {start()}
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