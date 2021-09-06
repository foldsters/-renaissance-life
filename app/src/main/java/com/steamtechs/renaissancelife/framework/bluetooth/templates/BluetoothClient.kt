package com.steamtechs.renaissancelife.framework.bluetooth.templates

abstract class BluetoothClient : Thread() {
    abstract override fun run()
}