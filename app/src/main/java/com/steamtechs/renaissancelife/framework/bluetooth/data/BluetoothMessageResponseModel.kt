package com.steamtechs.renaissancelife.framework.bluetooth.data

import kotlinx.serialization.Serializable

@Serializable
class BluetoothMessageResponseModel (
     val header : String?,
     val message : String,
     val deviceName : String?,
     val deviceAddress : String?
)