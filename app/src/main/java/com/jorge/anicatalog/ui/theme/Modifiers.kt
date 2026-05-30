package com.jorge.anicatalog.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Textura de pontos halftone
fun Modifier.dotTexture(
    color: Color = Color(0xFF3A2E1E),
    radius: Float = 1f,
    spacing: Float = 14f,
    alpha: Float = 0.055f
): Modifier = this.drawBehind {
    val cols = (size.width / spacing).toInt() + 2
    val rows = (size.height / spacing).toInt() + 2
    for (col in 0..cols) {
        for (row in 0..rows) {
            drawCircle(
                color = color.copy(alpha = alpha),
                radius = radius,
                center = Offset(col * spacing, row * spacing)
            )
        }
    }
}

// Dobra de página (canto superior direito)
fun Modifier.pageCornerFold(
    foldSize: Float = 48f,
    outerColor: Color = Color(0xFFC8C0B0),
    innerColor: Color = Color(0xFFE8E0D0)
): Modifier = this.drawBehind {
    val path = Path().apply {
        moveTo(size.width - foldSize, 0f)
        lineTo(size.width, 0f)
        lineTo(size.width, foldSize)
        close()
    }
    drawPath(path, outerColor)
    val inner = foldSize - 3f
    val pathInner = Path().apply {
        moveTo(size.width - inner, 0f)
        lineTo(size.width, 0f)
        lineTo(size.width, inner)
        close()
    }
    drawPath(pathInner, innerColor)
}

// Lombada esquerda (listras horizontais)
fun Modifier.spineDecoration(
    color: Color = Color(0xFFC8C0B0),
    width: Float = 8f,
    gap: Float = 8f,
    alpha: Float = 0.5f
): Modifier = this.drawBehind {
    var y = 0f
    while (y < size.height) {
        drawLine(
            color = color.copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(width, y),
            strokeWidth = 1.5f
        )
        y += gap
    }
}

// Borda tracejada (para status "assistindo" e "pausado")
fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Float = 1f,
    dashWidth: Float = 4f,
    gapWidth: Float = 3f,
    cornerRadius: Dp = 0.dp
): Modifier = this.drawBehind {
    drawRoundRect(
        color = color,
        style = Stroke(
            width = strokeWidth,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)
        ),
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}