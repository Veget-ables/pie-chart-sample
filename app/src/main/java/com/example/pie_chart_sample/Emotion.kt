package com.example.pie_chart_sample

import androidx.compose.ui.graphics.Color

internal enum class Emotion {
    Joy,
    Trust,
    Fear,
    Surprise,
    Sadness,
    Disgust,
    Anger,
    Anticipation,
}

internal val Emotion.color: Color
    get() = when(this){
        Emotion.Joy -> Yellow500
        Emotion.Trust -> LightGreen500
        Emotion.Fear -> Green500
        Emotion.Surprise -> Blue500
        Emotion.Sadness -> Indigo500
        Emotion.Disgust -> Purple500
        Emotion.Anger -> Red500
        Emotion.Anticipation -> Orange500
    }

private val Red500 = Color(0xFFF44336)
private val Purple500 = Color(0xFF9C27B0)
private val Indigo500 = Color(0xFF3F51B5)
private val Blue500 = Color(0xFF2196F3)
private val Green500 = Color(0xFF4CAF50)
private val LightGreen500 = Color(0xFF8BC34A)
private val Yellow500 = Color(0xFFFFEB3B)
private val Orange500 = Color(0xFFFF9800)
