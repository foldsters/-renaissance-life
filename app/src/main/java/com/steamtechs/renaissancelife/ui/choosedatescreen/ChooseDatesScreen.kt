package com.steamtechs.renaissancelife.ui.choosedatescreen


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.constraintlayout.compose.ConstraintLayout
import java.text.SimpleDateFormat
import java.util.*

@Composable
@Preview
fun ChooseDatesScreen(){

    var datePicked : String? by remember {
        mutableStateOf(null)
    }

    val updatedDate = { date : Long? ->
        datePicked = dateFormater(date)
    }




    val modifier = Modifier

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (chooseDatesText,
            startDateText,
            startPicker,
            finalDateText,
            finalPicker,
            buttonRow
        ) = createRefs()

        Text(text = "Choose Dates!",
        modifier = modifier.constrainAs(chooseDatesText){
            top.linkTo(parent.top,16.dp)
        }, fontSize = 12.em)

        Text(text = "Start: ",
            modifier = modifier.constrainAs(startDateText){
                top.linkTo(chooseDatesText.bottom,16.dp)
            }, fontSize = 12.em
        )

        DatePicker( datePicked = datePicked,
        modifier = modifier.constrainAs(startPicker){
            top.linkTo(startDateText.bottom, 16.dp)
            start.linkTo(parent.start)
        }){updatedDate}

//        Box(modifier = modifier
//            .constrainAs(startPicker) {
//                top.linkTo(startDateText.bottom, 16.dp)
//            }
//            .border(width = 1.dp, color = Black, shape = RoundedCornerShape(2.dp))
//            .clickable {  }
//        ){
//            Text(text = "StartDate", fontSize = 12.em)}


        Text(text = "Final",
            modifier = modifier.constrainAs(finalDateText){
                top.linkTo(startPicker.bottom,16.dp)
            }, fontSize = 12.em)

        Box(modifier = modifier
            .constrainAs(finalPicker) {
                top.linkTo(finalDateText.bottom, 16.dp)
            }
            .border(width = 1.dp, color = Black, shape = RoundedCornerShape(2.dp))
        ){Text(text = "FinalDate", fontSize = 12.em)}



        Row(modifier = modifier
            .constrainAs(buttonRow) {
                bottom.linkTo(parent.bottom, 16.dp)
            }
            .constrainAs(buttonRow) {
                start.linkTo(parent.absoluteLeft)
            }) {
            Button(onClick = { /*TODO*/ },
                content = {Text(text = "Logging Screen")},
                modifier = modifier
                    .padding(5.dp)
                    .wrapContentSize()
                    .weight(1f)
                )

            Button(onClick = { /*TODO*/ },
                content = {Text(text = "Radar Screen")},
                modifier = modifier
                    .padding(5.dp)
                    .wrapContentSize()
                    .weight(1f)
                )

        }
    }


}

fun dateFormater(milliseconds : Long?) : String?{
    milliseconds?.let{
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = it
        return formatter.format(calendar.time)
    }
    return null
}