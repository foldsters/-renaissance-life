package com.steamtechs.renaissancelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothExample
import com.steamtechs.renaissancelife.ui.composables.dateselectscreen.DatePickerExample
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent { DatePickerExample() }

        //setContent { MainComposable() }
        setContent { BluetoothExample() }

    }

    override fun onResume() {
        super.onResume()
        //BluetoothHandlerObject.startBluetoothServer()
    }

    override fun onPause() {
        super.onPause()
        //BluetoothHandlerObject.stopBluetoothServer()
    }
}
