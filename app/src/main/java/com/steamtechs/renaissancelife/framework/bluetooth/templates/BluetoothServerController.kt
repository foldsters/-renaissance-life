package com.steamtechs.renaissancelife.framework.bluetooth.templates

abstract class BluetoothServerController : Thread() {

    abstract override fun run()
    abstract fun cancel()

}