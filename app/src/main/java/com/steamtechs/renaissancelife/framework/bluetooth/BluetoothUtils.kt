package com.steamtechs.renaissancelife.framework.bluetooth

import java.util.*

val BluetoothUUID : UUID = UUID.fromString("fb5f7580-d51f-4e5d-b070-99edc1eaf1d4")

// Data class containing information about a message received from the server
data class ReceivedMessageData (
    val time: Long,
    val deviceAddress: String?,
    val message: String
)
