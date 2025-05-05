package com.example.pattern.ui.components

import android.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ExtraCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .height(240.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 100.dp))
            HalfCircularProgressBar(
                percentage = 0.90f,
                number = 100
            )
        }
}
}
@Composable
fun HalfCircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 32.sp,
    width: Dp = 180.dp,
    height: Dp = 120.dp,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurfaceVariant,
    strokeWidth: Dp = 12.dp,
    animDuration: Int = 3000,
    animDelay: Int = 0,
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ), label = ""
    )
    LaunchedEffect(Unit) {
        animationPlayed = true
    }
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(width = width, height = height)) {
            val arcSize = Size(size.width, size.height * 2) // Makes it visually a half-circle
            val arcOffset = Offset(0f, -size.height)
            // Draw background arc
            drawArc(
                color = backgroundColor,
                startAngle = -180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                size = arcSize,
                topLeft = arcOffset
            )
            // Draw animated foreground arc
            drawArc(
                color = color,
                startAngle = -180f,
                sweepAngle = 180f * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                size = arcSize,
                topLeft = arcOffset
            )
        }
        Text(
            text = (curPercentage.value * number).toInt().toString(),
            fontSize = fontSize,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily
        )
    }
}


