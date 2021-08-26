package com.steamtechs.renaissancelife.ui.composables

import android.graphics.Paint
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun Radar(summary : Pair<List<String>, List<Float>>) {

    val (summaryTitles, summaryIntensities) = summary

    val (spokePoints, namePoints, intensityPoints) =
        generateRadarPoints(summaryIntensities) ?: return

    Canvas(modifier = Modifier.fillMaxSize()) {

        val cx = size.center.x
        val cy = size.center.y
        val scale = size.minDimension * 0.25f

        drawRadarShape(
            points = intensityPoints,
            scale = scale,
            cx = cx,
            cy = cy,
            strokeColor = Color(0xFF0000FF),
            fillColor = Color(0x552288FF)
        )

        drawSpokes(
            spokes = spokePoints,
            scale = scale,
            cx = cx,
            cy = cy,
            color = Color(0xFF888888)
        )

        drawTitles(
            nameSpokes = namePoints,
            summaryTitles = summaryTitles,
            scale = scale,
            cx = cx,
            cy = cy,
            textScaleFactor = 0.15f,
            lineHeightFactor = 0.20f,
            titleColor = Color(0xff0000ff)
        )
    }
}


// Data

private data class Spoke(
    val x: Float,
    val y: Float
)

private data class RadarPoints(
    val spokePoints: List<Spoke>,
    val namePoints: List<Spoke>,
    val intensityPoints: List<Spoke>
)


// Setup

private fun generateRadarPoints(summaryIntensities : List<Float>) : RadarPoints? {

    if (summaryIntensities.isEmpty()) return null

    return RadarPoints(
        generateSpokes( List(summaryIntensities.size) {1.0f} ),
        generateSpokes( List(summaryIntensities.size) {1.5f} ),
        generateSpokes( summaryIntensities )
    )
}

private fun generateSpokes(intensities : List<Float>) : List<Spoke> =
    intensities.mapIndexed { index, intensity ->
        val angle: Float = 6.28f * index / intensities.size
        Spoke(cos(angle) * intensity, sin(angle) * intensity)
    }


// Drawing

private fun DrawScope.drawRadarShape(
    points: List<Spoke>,
    scale: Float,
    cx: Float,
    cy: Float,
    strokeColor: Color,
    fillColor: Color
) {
    val radarPath = Path().apply {
        points.forEachIndexed { i, p ->
            println("$i, $p")
            if (i == 0)
                moveTo(p.x * scale + cx, p.y * scale + cy)
            else
                lineTo(p.x * scale + cx, p.y * scale + cy)
        }
        close()
    }

    drawPath(
        radarPath,
        strokeColor,
        style = Stroke(5f, join = StrokeJoin.Round)
    )

    drawPath(
        radarPath,
        fillColor,
        style = Fill
    )
}

private fun DrawScope.drawSpokes(
    spokes: List<Spoke>,
    scale: Float,
    cx: Float,
    cy: Float,
    color: Color
) {
    val spokePath = Path().apply {
        spokes.forEach { sp ->
            moveTo(cx, cy)
            lineTo(sp.x * scale + cx, sp.y * scale + cy)
        }
    }

    drawPath(
        spokePath,
        color,
        style = Stroke(
            width = 5f,
            join = StrokeJoin.Round)
    )
}


private fun DrawScope.drawTitles(
    nameSpokes: List<Spoke>,
    summaryTitles: List<String>,
    scale: Float,
    cx: Float,
    cy: Float,
    textScaleFactor: Float,
    lineHeightFactor: Float,
    titleColor: Color
) {

    val textPaint = Paint().apply {
        textSize = textScaleFactor * scale
        color = titleColor.toArgb()
        textAlign = Paint.Align.CENTER
    }

    nameSpokes.forEachIndexed { i, it ->

        val title = summaryTitles[i].replace(" &", "&")
        val titleList = title.split(" ")
        val newTitle = StringBuilder()
        val yOff = lineHeightFactor * scale
        var yLine = 0

        fun drawTitle(t : StringBuilder) =
            drawContext.canvas.nativeCanvas.drawText(
                t.toString(),
                it.x * scale + cx,
                it.y * scale + cy + yOff * yLine,
                textPaint
            )

        // Line return logic
        titleList.forEach { t ->
            newTitle.append(t.replace("&", " &"))
            if (newTitle.length > 5) {
                drawTitle(newTitle)
                newTitle.clear()
                yLine += 1
            } else {
                newTitle.append(' ')
            }
        }

        drawTitle(newTitle)
    }
}