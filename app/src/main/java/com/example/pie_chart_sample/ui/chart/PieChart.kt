package com.example.pie_chart_sample.ui.chart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import kotlin.math.cos
import kotlin.math.sin

data class Piece(
    val proportion: Float,
    val backgroundColor: Color,
    val label: String,
    val labelColor: Color = Color.White
)

@Composable
fun PieChart(
    modifier: Modifier,
    pieces: List<Piece>,
    labelFontSize: Dp,
) {

    val endAngles = pieces.map { piece ->
        360 * piece.proportion / 100
    }

    Canvas(
        modifier = modifier
    ) {
        drawPie(pieces = pieces, endAngles = endAngles)
        drawLabel(pieces = pieces, endAngles = endAngles, fontSize = labelFontSize)
    }
}

private fun DrawScope.drawPie(pieces: List<Piece>, endAngles: List<Float>) {
    var startAngle = 270f

    pieces.forEachIndexed { index, piece ->
        val sweepAngle = endAngles[index]

        drawArc(
            color = piece.backgroundColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            size = size,
            style = Fill,
        )
        startAngle += endAngles[index]
    }
}

private fun DrawScope.drawLabel(pieces: List<Piece>, endAngles: List<Float>, fontSize: Dp) {
    var startAngle = 270f
    val canvasSize = size.width

    pieces.forEachIndexed { index, piece ->
        val sweepAngle = endAngles[index]

        val centerAngle = startAngle + (sweepAngle / 2)
        val radian = (centerAngle * Math.PI) / 180

        val proportionX = ((canvasSize / 2) + (cos(radian) * canvasSize / 4)).toFloat()
        val proportionY = ((canvasSize / 2) + (sin(radian) * canvasSize / 4)).toFloat()
        val labelX = ((canvasSize / 2) + (cos(radian) * canvasSize / 2.5)).toFloat()
        val labelY = ((canvasSize / 2) + (sin(radian) * canvasSize / 2.5)).toFloat()

        drawCircle(
            color = Color.Black,
            radius = 10f,
            center = Offset(proportionX, proportionY)
        )
        drawCircle(color = Color.Black, radius = 10f, center = Offset(labelX, labelY))

        val paint = Paint().asFrameworkPaint().apply {
            color = piece.labelColor.toArgb()
            textSize = fontSize.toPx()
            textAlign = android.graphics.Paint.Align.CENTER
        }

        drawIntoCanvas {
            it.nativeCanvas.drawText(
                "${piece.proportion}%",
                proportionX,
                proportionY,
                paint
            )
            it.nativeCanvas.drawText(
                piece.label,
                labelX,
                labelY,
                paint
            )
        }

        startAngle += endAngles[index]
    }
}