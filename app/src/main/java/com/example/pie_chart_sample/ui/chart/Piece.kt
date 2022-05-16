package com.example.pie_chart_sample.ui.chart

import androidx.compose.ui.graphics.Color

data class Piece(
    val size: Float,
    val backgroundColor: Color,
    val title: String,
    val labelColor: Color = Color.Black
)
