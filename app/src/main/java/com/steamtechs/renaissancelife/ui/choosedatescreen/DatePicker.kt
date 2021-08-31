package com.steamtechs.renaissancelife.ui.choosedatescreen

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun DatePicker(datePicked : String?,
               modifier: Modifier,
               updatedDate : ( date : Long? ) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .padding(top = 10.dp)
            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
            .clickable{
                showDatePicker(activity, updatedDate)
            }
    ) {

        Text(
            text= datePicked?:"Date Picker",
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .fillMaxWidth()
        )

        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp, 20.dp),
            tint = MaterialTheme.colors.onSurface
        )

    }
}



private fun showDatePicker(
    activity : AppCompatActivity,
    updatedDate: (Long?) -> Unit)
{
    val picker = MaterialDatePicker.Builder.datePicker().build()
    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        updatedDate(it)
    }
}
