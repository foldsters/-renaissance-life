package com.steamtechs.renaissancelife.framework.bluetooth.interactors

import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothDatasource
import com.steamtechs.renaissancelife.framework.bluetooth.data.BluetoothMessageResponseModel

class BluetoothRepository(private val bluetoothDatasource : BluetoothDatasource) {

    fun getDevices() : HashMap<String, String> {
        return bluetoothDatasource.getDevices()
    }

    fun sendMessageToDevice(deviceAddress: String?, message: String, header: String?) {
        bluetoothDatasource.sendMessageToDevice(deviceAddress, message, header)
    }

    fun startBluetoothServer() {
        bluetoothDatasource.startBluetoothServer()
    }

    fun stopBluetoothServer() {
        bluetoothDatasource.stopBluetoothServer()
    }

    fun registerBluetoothMessageResponseCallback(header : String, callback : (BluetoothMessageResponseModel) -> Unit) {
        bluetoothDatasource.registerBluetoothMessageResponseCallback(header, callback)
    }

}