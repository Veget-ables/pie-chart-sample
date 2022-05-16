package com.example.pie_chart_sample.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pie_chart_sample.Emotion

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
internal fun EmotionFilterChips(
    emotions: List<Emotion>,
    onChipClick: (Emotion) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        modifier = modifier,
    ) {
        items(Emotion.values()) { emotion ->
            Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                FilterChip(
                    selected = emotions.contains(emotion),
                    selectedIcon = {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        onChipClick(emotion)
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = emotion.title)
                }
            }
        }
    }
}
