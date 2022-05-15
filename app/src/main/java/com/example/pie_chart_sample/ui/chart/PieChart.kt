package com.example.pie_chart_sample.ui.chart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PieChart(
    modifier: Modifier,
    pieces: List<Piece>,
    labelFontSize: Dp,
) {
    val sweepAngles = pieces.map { piece ->
        360 * piece.proportion / 100
    }

    Canvas(
        modifier = modifier
    ) {
        drawPie(pieces = pieces, sweepAngles = sweepAngles)
        drawLabels(pieces = pieces, sweepAngles = sweepAngles, fontSize = labelFontSize)
    }
}

private fun DrawScope.drawPie(pieces: List<Piece>, sweepAngles: List<Float>) {
    var startAngle = 270f

    pieces.forEachIndexed { index, piece ->
        val sweepAngle = sweepAngles[index]

        drawArc(
            color = piece.backgroundColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            size = size,
            style = Fill,
        )
        startAngle += sweepAngles[index]
    }
}

private fun DrawScope.drawLabels(pieces: List<Piece>, sweepAngles: List<Float>, fontSize: Dp) {
    var startAngle = 270f
    val canvasHalfWidth = size.width / 2
    val canvasHalfHeight = size.height / 2

    pieces.forEachIndexed { index, piece ->
        val paint = Paint().asFrameworkPaint().apply {
            color = piece.labelColor.toArgb()
            textSize = fontSize.toPx()
            textAlign = android.graphics.Paint.Align.CENTER
        }

        drawIntoCanvas {
            val sweepAngle = sweepAngles[index]

            val centerAngle = startAngle + (sweepAngle / 2)
            val radian = (centerAngle * Math.PI) / 180

            val labelDistXFromCenter = cos(radian) * canvasHalfWidth * 0.75
            val labelDistYFromCenter = sin(radian) * canvasHalfHeight * 0.75

            val proportionX = canvasHalfWidth + labelDistXFromCenter
            val proportionY = canvasHalfWidth + labelDistYFromCenter - (fontSize.toPx() / 2)

            it.nativeCanvas.drawText(
                "${piece.proportion}%",
                proportionX.toFloat(),
                proportionY.toFloat(),
                paint
            )

            val titleX = canvasHalfWidth + labelDistXFromCenter
            val titleY = canvasHalfWidth + labelDistYFromCenter + (fontSize.toPx() / 2)

            it.nativeCanvas.drawText(
                piece.title,
                titleX.toFloat(),
                titleY.toFloat(),
                paint
            )
        }

        startAngle += sweepAngles[index]
    }
}
