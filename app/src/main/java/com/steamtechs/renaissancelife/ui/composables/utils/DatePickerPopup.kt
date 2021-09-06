package com.steamtechs.renaissancelife.ui.composables.utils

import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun DatePickerPopup(onConfirmDate : (String) -> Unit, onDismiss : () -> Unit, startDate : String? = null) {

    var date = startDate ?: LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) // If no date selected, return today

    AlertDialog (
            onDismissRequest = onDismiss,
            text = { DatePicker(date) { date = it } },
            confirmButton = {
                Button(onClick = { onConfirmDate(date) }) {
                    Text("Select Date")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Dismiss")
                }
            }
        )
}

@Composable
fun DatePicker(startDate : String, dateStringCallback : (String) -> Unit) {

    val dateLong = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(startDate)!!.time

    AndroidView (
        factory = { CalendarView(it).apply{ date = dateLong } },
        modifier = Modifier.wrapContentWidth(),
        update = { views ->
            views.setOnDateChangeListener { _, year, month, day ->
                val padMonth = (month + 1).toString().padStart(2, '0')
                val padDay   = day.toString().padStart(2, '0')
                dateStringCallback("$year-$padMonth-$padDay")
            }
        }
    )
}



