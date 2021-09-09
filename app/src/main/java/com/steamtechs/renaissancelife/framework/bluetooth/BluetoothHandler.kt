package com.steamtechs.renaissancelife.framework.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.templates.BluetoothServerController
import java.time.Instant

class BluetoothHandler(private val bluetoothServerControllerConstructor : (BluetoothMessageCallback) -> BluetoothServerController,
                       private val bluetoothClientConstructor : (BluetoothDevice?, String, String?) -> BluetoothClient) {


    // Map of all the devices this devices is paired with
    private var _devicesMap = HashMap<String, BluetoothDevice>()

    // The messages that the server has received
    var receivedMessagesData : MutableLiveData<List<BluetoothMessageResponseModel>> = MutableLiveData( listOf() )

    // A server instance
    private var server : BluetoothServerController? = null

    // Public device map, getter gets all the paired devices
    val devicesMap : HashMap<String, BluetoothDevice>?
        get() {
            val btAdapter = BluetoothAdapter.getDefaultAdapter()

            if (btAdapter == null || !btAdapter.isEnabled) {

                println("BLUETOOTH IS DISABLED")
                return null

            }

            for (device in BluetoothAdapter.getDefaultAdapter().bondedDevices) {
                _devicesMap[device.address] = device
            }

            return _devicesMap
        }

    // Broadcast Receiver to add newly paired devices to the device map
    var bluetoothBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND == action) {
                // Get the BluetoothDevice object from the Intent
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                        ?: return

                _devicesMap[device.address] = device
            }
        }
    }

    // Makes a client connection and sends message to the device's server
    fun sendMessageToDevice(device : BluetoothDevice?, message : String, header : String? = null) {

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
    fun startBluetoothServerController() {
        if (server == null) {
            server = bluetoothServerControllerConstructor(::innerMessageReceiveCallback).apply {start()}
        }
    }

    // Stops the bluetooth server
    // This is to be called from the activity onPause
    fun stopBluetoothServer() {
        server?.cancel()
        server = null
    }


    private val bluetoothCallbackRoster : MutableMap<String, MutableList<BluetoothMessageCallback>> = mutableMapOf()

    fun registerBluetoothMessageResponseCallback(header : String, callback : BluetoothMessageCallback) : () -> Unit {

        bluetoothCallbackRoster.putIfAbsent(header, mutableListOf())
        bluetoothCallbackRoster[header]!!.add(callback)

        return { bluetoothCallbackRoster[header]?.remove(callback) } // Unregister callback
    }

    // Used as a callback for the server
    // When the server receives a message, it adds it to the receivedMessages list
    private fun innerMessageReceiveCallback(btResponseModel: BluetoothMessageResponseModel) {
        println("CALLBACK CALLED $btResponseModel")
        val updatedReceivedMessages = receivedMessagesData.value?.plus(listOf(btResponseModel)) ?: listOf()
        receivedMessagesData.postValue( updatedReceivedMessages )

        // Update callback roster
        bluetoothCallbackRoster[btResponseModel.header]?.forEach { it(btResponseModel) }

    }
}