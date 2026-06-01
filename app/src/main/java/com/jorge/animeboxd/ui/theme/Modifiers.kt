package com.jorge.animeboxd.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke

fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Float = 1f,
    dashWidth: Float = 4f,
    gapWidth: Float = 3f
): Modifier = this.drawBehind {
    drawRoundRect(
        color = color,
        style = Stroke(
            width = strokeWidth,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)
        )
    )
}