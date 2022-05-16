package com.example.pie_chart_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pie_chart_sample.ui.EmotionFilterChips
import com.example.pie_chart_sample.ui.chart.PieChart
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
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val emotions: MutableList<Emotion> = remember {
                            Emotion.values().toList().toMutableStateList()
                        }

                        PieChart(
                            modifier = Modifier
                                .size(300.dp),
                            pieces = emotions.chartPieces,
                            labelFontSize = 16.dp
                        )

                        Spacer(Modifier.height(20.dp))

                        EmotionFilterChips(
                            emotions = emotions,
                            onChipClick = {
                                if (emotions.contains(it)) {
                                    emotions.remove(it)
                                } else {
                                    emotions.add(it)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
