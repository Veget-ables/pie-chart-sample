package com.example.pie_chart_sample

import androidx.compose.ui.graphics.Color
import com.example.pie_chart_sample.ui.chart.Piece

internal enum class Emotion(val title: String, val color: Color, val size: Float) {
    Joy(title = "喜び", color = Yellow500, size = 30f),
    Trust(title = "信頼", color = LightGreen500, size = 20f),
    Fear(title = "恐れ", color = Green500, size = 16f),
    Surprise(title = "驚き", color = Blue500, size = 15f),
    Sadness(title = "悲しみ", color = Indigo500, size = 10f),
    Disgust(title = "嫌悪", color = Purple500, size = 8f),
    Anger(title = "怒り", color = Red500, size = 5f),
    Anticipation(title = "期待", color = Orange500, size = 3f),
}

internal val List<Emotion>.chartPieces: List<Piece>
    get() = map {
        Piece(
            size = it.size,
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
