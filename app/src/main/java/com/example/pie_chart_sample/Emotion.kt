package com.example.pie_chart_sample

import androidx.compose.ui.graphics.Color

internal enum class Emotion(val label: String, val color: Color, val proportion: Float) {
    Joy(label = "joy", color = Yellow500, proportion = 20f),
    Trust(label = "trust", color = LightGreen500, proportion = 16f),
    Fear(label = "fear", color = Green500, proportion = 12f),
    Surprise(label = "surprise", color = Blue500, proportion = 15f),
    Sadness(label = "sadness", color = Indigo500, proportion = 20f),
    Disgust(label = "disgust", color = Purple500, proportion = 8f),
    Anger(label = "anger", color = Red500, proportion = 5f),
    Anticipation(label = "anticipation", color = Orange500, proportion = 4f),
}

private val Red500 = Color(0xFFF44336)
private val Purple500 = Color(0xFF9C27B0)
private val Indigo500 = Color(0xFF3F51B5)
private val Blue500 = Color(0xFF2196F3)
private val Green500 = Color(0xFF4CAF50)
private val LightGreen500 = Color(0xFF8BC34A)
private val Yellow500 = Color(0xFFFFEB3B)
private val Orange500 = Color(0xFFFF9800)
