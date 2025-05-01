package com.example.pattern.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LinearProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 20.sp,
    width: Dp = 240.dp,
    height: Dp = 20.dp,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.tertiary,
    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface,
    animDuration: Int = 3000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val animatedProgress = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(50))
                .background(backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress.value)
                    .background(color)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${(animatedProgress.value * number).toInt()}",
            fontSize = fontSize,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily
        )
    }
}
