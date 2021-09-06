package com.steamtechs.renaissancelife.ui.composables.dateselectscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.steamtechs.renaissancelife.ui.AppViewModel
import com.steamtechs.renaissancelife.ui.composables.utils.DatePickerPopup
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun DatePickerExample() {

    val today = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)

    val appViewModel = viewModel<AppViewModel>()

    var showPopup1 by remember { mutableStateOf(false) }
    val startDate1 by appViewModel.startDate.observeAsState(today)

    var showPopup2 by remember { mutableStateOf(false) }
    val startDate2 by appViewModel.endDate.observeAsState(today)

    val onStartDateChange = appViewModel::onStartDateChange
    val onEndDateChange   = appViewModel::onEndDateChange



    if (showPopup1) {
        DatePickerPopup(
            onConfirmDate = {
                onStartDateChange(it)
                showPopup1 = false
            },
            onDismiss = { showPopup1 = false },
            startDate = startDate1
        )
    }

    if (showPopup2) {
        DatePickerPopup(
            onConfirmDate = {
                onEndDateChange(it)
                showPopup2 = false
            },
            onDismiss = { showPopup2 = false },
            startDate = startDate2
        )
    }

    Column {


        Row {
            Button(onClick = { showPopup1 = true }) {
                Text("Select Start Date")
            }
            Text(startDate1)
        }

        Row {
            Button(onClick = { showPopup2 = true }) {
                Text("Select End Date")
            }
            Text(startDate2)
        }

    }
}