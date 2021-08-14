package com.steamtechs.renaissancelife.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em


@Composable
fun CategoryRow(categoryName: String = "") {

    val modifier = Modifier
    var categoryInt by remember { mutableStateOf(0) }

    Row(
        modifier = modifier.height(75.dp),
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Category $categoryName",
            textAlign = TextAlign.Center,
            modifier = modifier.weight(2f)
        )
        Button(
            onClick = {--categoryInt},
            modifier.weight(1f).fillMaxHeight().padding(5.dp)
        ) {
            Text(
                "-",
                fontSize = 8.em,
                fontWeight = FontWeight.ExtraBold,
                modifier = modifier
            )

        }
        Text(
            text = categoryInt.toString(),
            textAlign = TextAlign.Center,
            fontSize = 10.em,
            fontWeight = FontWeight.Bold,
            modifier = modifier.weight(1f)
        )
        Button(
            onClick = {++categoryInt
                      println("Category $categoryName has Int value: $categoryInt")},
            modifier.weight(1f).fillMaxHeight().padding(5.dp)
        ) {
            Text("+", fontSize = 8.em)
        }
    }
}