package com.example.pie_chart_sample

import androidx.compose.ui.graphics.Color
import com.example.pie_chart_sample.ui.chart.Piece

internal enum class Emotion(val title: String, val color: Color, val proportion: Float) {
    Joy(title = "喜び", color = Yellow500, proportion = 20f),
    Trust(title = "信頼", color = LightGreen500, proportion = 16f),
    Fear(title = "恐れ", color = Green500, proportion = 12f),
    Surprise(title = "驚き", color = Blue500, proportion = 15f),
    Sadness(title = "悲しみ", color = Indigo500, proportion = 20f),
    Disgust(title = "嫌悪", color = Purple500, proportion = 8f),
    Anger(title = "怒り", color = Red500, proportion = 5f),
    Anticipation(title = "期待", color = Orange500, proportion = 4f),
}

internal val emotionChartPieces = Emotion.values().map {
    Piece(
        proportion = it.proportion,
        backgroundColor = it.color,
        title = it.title
    )
}

private val Red500 = Color(0xFFF44336)
private val Purple500 = Color(0xFF9C27B0)
private val Indigo500 = Color(0xFF3F51B5)
private val Blue500 = Color(0xFF2196F3)
private val Green500 = Color(0xFF4CAF50)
private val LightGreen500 = Color(0xFF8BC34A)
private val Yellow500 = Color(0xFFFFEB3B)
private val Orange500 = Color(0xFFFF9800)
