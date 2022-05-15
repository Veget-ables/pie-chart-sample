package com.example.pie_chart_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pie_chart_sample.ui.chart.PieChart
import com.example.pie_chart_sample.ui.chart.Piece
import com.example.pie_chart_sample.ui.theme.PiechartsampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PiechartsampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(Modifier.fillMaxSize()) {
                        PieChart(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(300.dp),
                            pieces = emotionChartPieces,
                            labelFontSize = 16.dp
                        )
                    }
                }
            }
        }
    }
}

val emotionChartPieces = Emotion.values().map {
    Piece(
        proportion = it.proportion,
        backgroundColor = it.color,
        title = it.title
    )
}
