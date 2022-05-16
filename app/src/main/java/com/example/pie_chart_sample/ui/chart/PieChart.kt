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
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
internal fun PieChart(
    modifier: Modifier,
    pieces: List<Piece>,
    labelFontSize: Dp,
) {
    val totalSize = pieces.map { it.size }.sum()

    // 各扇形のSizeが全体の何割に当たるのかを算出
    val proportions = pieces.map { piece ->
        piece.size * 100 / totalSize
    }
    // 割合から各扇形の中心角を算出
    val sweepAngles = proportions.map { proportion ->
        360 * proportion / 100
    }

    Canvas(
        modifier = modifier
    ) {
        // 全ての扇形を描画してPieを作る
        drawPie(
            pieces = pieces,
            sweepAngles = sweepAngles
        )
        // 各扇形の上にラベルを描画する
        drawLabels(
            pieces = pieces,
            sweepAngles = sweepAngles,
            proportions = proportions,
            fontSize = labelFontSize
        )
    }
}

private fun DrawScope.drawPie(pieces: List<Piece>, sweepAngles: List<Float>) {
    // 円の真上から扇形を描画し始めるので開始地点の角度は270°
    var startAngle = 270f

    pieces.forEachIndexed { index, piece ->
        val sweepAngle = sweepAngles[index]
        // 扇形を描画する。startAngleの位置からsweepAngleの中心角まで伸びた扇形を描画する
        drawArc(
            color = piece.backgroundColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            size = size,
            style = Fill,
        )
        // startAngleに描画した扇形の中心角を加算して次の扇形の描画開始位置を指定する
        startAngle += sweepAngle
    }
}

private fun DrawScope.drawLabels(
    pieces: List<Piece>,
    sweepAngles: List<Float>,
    proportions: List<Float>,
    fontSize: Dp
) {
    var startAngle = 270f
    val canvasHalfWidth = size.width / 2
    val canvasHalfHeight = size.height / 2

    pieces.forEachIndexed { index, piece ->
        val paint = Paint().asFrameworkPaint().apply {
            color = piece.labelColor.toArgb()
            textSize = fontSize.toPx()
            textAlign = android.graphics.Paint.Align.CENTER
        }

        // 扇形の中央から少し外側にずらした位置にラベル（割合の数字とタイトル）を描画する。
        // ラベルの基準の位置になるx,y座標は三角関数で算出。基準の位置を中心に割合の数字とタイトルが2行で並ぶように位置を調整
        drawIntoCanvas {
            val sweepAngle = sweepAngles[index]

            val centerAngle = startAngle + (sweepAngle / 2)
            val radian = (centerAngle * Math.PI) / 180

            val labelDistXFromCenter = cos(radian) * canvasHalfWidth * 0.75
            val labelDistYFromCenter = sin(radian) * canvasHalfHeight * 0.75

            val proportionX = canvasHalfWidth + labelDistXFromCenter
            val proportionY = canvasHalfHeight + labelDistYFromCenter - (fontSize.toPx() / 2)

            it.nativeCanvas.drawText(
                "${proportions[index].roundToInt()}%",
                proportionX.toFloat(),
                proportionY.toFloat(),
                paint
            )

            val titleX = canvasHalfWidth + labelDistXFromCenter
            val titleY = canvasHalfHeight + labelDistYFromCenter + (fontSize.toPx() / 2)

            it.nativeCanvas.drawText(
                piece.title,
                titleX.toFloat(),
                titleY.toFloat(),
                paint
            )

            startAngle += sweepAngle
        }
    }
}
