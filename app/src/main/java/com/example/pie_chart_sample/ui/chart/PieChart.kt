package com.example.pie_chart_sample.ui.chart

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun PieChart(
    modifier: Modifier,
    progress: List<Float>,
    colors: List<Color>,
    percentColor: Color = Color.White
) {
    if (progress.isEmpty() || progress.size != colors.size) return

    val total = progress.sum()
    val proportions = progress.map {
        it * 100 / total
    }
    val angleProgress = proportions.map {
        360 * it / 100
    }

    val progressSize = mutableListOf<Float>()
    progressSize.add(angleProgress.first())

    for (x in 1 until angleProgress.size)
        progressSize.add(angleProgress[x] + progressSize[x - 1])

    var startAngle = 270f

    BoxWithConstraints(modifier = modifier) {

        val sideSize = min(constraints.maxWidth, constraints.maxHeight)
        val padding = (sideSize * 20) / 100f


        val pathPortion = remember {
            Animatable(initialValue = 0f)
        }
        LaunchedEffect(key1 = true) {
            pathPortion.animateTo(
                1f, animationSpec = tween(1000)
            )
        }

        val size = Size(
            width = sideSize.toFloat() - padding,
            height = sideSize.toFloat() - padding
        )

        Canvas(
            modifier = Modifier
                .width(sideSize.dp)
                .height(sideSize.dp)
        ) {
            angleProgress.forEachIndexed { index, arcProgress ->
                drawPie(
                    colors[index],
                    startAngle,
                    arcProgress * pathPortion.value,
                    size,
                    padding = padding,
                )
                startAngle += arcProgress

                drawContext.canvas.nativeCanvas.apply {
                    val fontSize = 60.toDp().toPx()
                    drawText(
                        "${proportions[index].roundToInt()}%",
                        (sideSize / 2) + fontSize / 4 + arcProgress,
                        (sideSize / 2) + fontSize / 3 + arcProgress,
                        Paint().apply {
                            color = percentColor.toArgb()
                            textSize = fontSize
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawPie(
    color: Color,
    startAngle: Float,
    arcProgress: Float,
    size: Size,
    padding: Float,
): Path {

    return Path().apply {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = arcProgress,
            useCenter = true,
            size = size,
            style = Fill,
            topLeft = Offset(padding / 2, padding / 2)
        )
    }
}

@Preview
@Composable
fun ChartPreview() {
    PieChart(
        modifier = Modifier,
        progress = listOf(10f, 20f, 5f),
        colors = listOf(
            Color(0xFFbf95d4),
            Color(0xFFf4ac1a),
            Color(0xFF8b0a50),
        )
    )
}
