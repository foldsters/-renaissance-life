package com.steamtechs.renaissancelife.framework.bluetooth.core

abstract class BluetoothServerController : Thread() {

    abstract override fun run()
    abstract fun cancel()

}