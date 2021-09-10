package com.steamtechs.renaissancelife.framework.bluetooth.data

import kotlinx.serialization.Serializable

@Serializable
class BluetoothMessageRequestModel (
    val header : String?,
    val message : String,
)