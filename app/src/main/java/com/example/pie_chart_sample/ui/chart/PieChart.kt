package com.example.pie_chart_sample.ui.chart

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
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
    var startAngle = 270f

    val endAngles = pieces.map { piece ->
        360 * piece.proportion / 100
    }
    Canvas(
        modifier = modifier
    ) {
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

            val canvasSize = size.width

            drawContext.canvas.nativeCanvas.apply {
                val centerAngle = startAngle + (sweepAngle / 2)
                val radian = (centerAngle * Math.PI) / 180
                val proportionX = cos(radian) * canvasSize / 4
                val proportionY = sin(radian) * canvasSize / 4

                val labelX = cos(radian) * canvasSize / 2.5
                val labelY = sin(radian) * canvasSize / 2.5

                drawText(
                    "${piece.proportion}%",
                    (canvasSize / 2) + proportionX.toFloat(),
                    (canvasSize / 2) + proportionY.toFloat(),
                    Paint().apply {
                        color = piece.labelColor.toArgb()
                        textSize = labelFontSize.toPx()
                        textAlign = Paint.Align.CENTER
                    }
                )
                drawText(
                    piece.label,
                    (canvasSize / 2) + labelX.toFloat(),
                    (canvasSize / 2) + labelY.toFloat(),
                    Paint().apply {
                        color = piece.labelColor.toArgb()
                        textSize = labelFontSize.toPx()
                        textAlign = Paint.Align.CENTER
                    }
                )
            }

            startAngle += endAngles[index]
        }
    }
}
