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

class BluetoothHandler(private val bluetoothServerControllerConstructor : ((String, String?) -> Unit) -> BluetoothServerController,
                       private val bluetoothClientConstructor : (BluetoothDevice?, String) -> BluetoothClient) {


    // Callback to be called when the server receives a message
    var messageReceiveCallback : ((String, String?) -> Unit)? = null

    // Map of all the devices this devices is paired with
    private var _devicesMap = HashMap<String, BluetoothDevice>()

    // The message for the client server to send
    var message : MutableLiveData<String> = MutableLiveData("")

    // The messages that the server has received
    var receivedMessagesData : MutableLiveData<List<ReceivedMessageData>> = MutableLiveData( listOf() )

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

    // Callback to update the message to send from the client
    fun onChangeMessage(newMessage : String) {
        message.value = newMessage
    }

    // Callback to clear the message
    fun clearMessage() {
        message.value = ""
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
    fun sendMessageToDevice(device : BluetoothDevice?) {

        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
        bluetoothClientConstructor(device, message.value ?: "NO MESSAGE SET").start()

    }

    // Makes a list of sendMessage functions, one for each paired device.
    val deviceCallbacks : List<()->Unit>
        get() {
            return _devicesMap.values.toList().map { { sendMessageToDevice(it) } }
        }

    // Starts the internal bluetooth server controller
    // This is to be called from the activity onResume
    fun startBluetoothServer() {
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

    // Used as a callback for the server
    // When the server receives a message, it adds it to the receivedMessages list
    private fun innerMessageReceiveCallback(receivedMessage : String, deviceAddress : String?) {
        println("CALLBACK CALLED $receivedMessage")
        val receivedMessageData = ReceivedMessageData(Instant.now().epochSecond, deviceAddress, receivedMessage)
        val updatedReceivedMessages = receivedMessagesData.value?.plus(listOf(receivedMessageData)) ?: listOf()
        receivedMessagesData.postValue( updatedReceivedMessages )
        messageReceiveCallback?.let { it(receivedMessage, deviceAddress) }
    }
}