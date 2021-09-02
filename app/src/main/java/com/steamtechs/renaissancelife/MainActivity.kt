package com.steamtechs.renaissancelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothHandler
import com.steamtechs.renaissancelife.ui.composables.mainscreen.MainComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainComposable() }

    }

    override fun onRestart() {
        super.onRestart()
        BluetoothHandler.startBluetoothServer()
    }

    override fun onPause() {
        super.onPause()
        BluetoothHandler.stopBluetoothServer()
    }
}
