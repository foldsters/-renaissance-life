package com.steamtechs.renaissancelife.framework.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData

object BluetoothHandler {

    private var _devicesMap = HashMap<String, BluetoothDevice>()

    var message : MutableLiveData<String> = MutableLiveData("")
    var receivedMessages : MutableLiveData<List<String>> = MutableLiveData( listOf() )
    private var server : BluetoothServerController? = null

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

    fun onSetMessage(newMessage : String) {
        message.value = newMessage
    }

    fun clearMessage() {
        message.value = ""
    }


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


    fun sendMessageToDevice(device : BluetoothDevice) {

        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
        println("BT DEVICE: ${device.address}")
        BluetoothClient(device, message.value ?: "NO MESSAGE SET").start()

    }

    val deviceCallbacks : List<()->Unit>
        get() {
            return _devicesMap.values.toList().map { { sendMessageToDevice(it) } }
        }

    fun startBluetoothServer() {
        if (server == null) {
            server = BluetoothServerController(::messageReceiveCallback).apply {start()}
        }
    }

    fun stopBluetoothServer() {
        server?.cancel()
        server = null
    }


    private fun messageReceiveCallback(newMessage : String) {
        println("CALLBACK CALLED $newMessage")
        val updatedReceivedMessages = receivedMessages.value?.plus(listOf(newMessage)) ?: listOf()
        receivedMessages.postValue( updatedReceivedMessages )
    }

}