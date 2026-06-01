package com.jorge.animeboxd.ui.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Particle(
    var x: Float,
    var y: Float,
    val radius: Float,
    val color: Color,
    val speedX: Float,
    val speedY: Float,
    var alpha: Float
)

@Composable
fun rememberParticles(
    count: Int = 20,
    colors: List<Color> = listOf(VioletLight, BlueLight)
): List<Particle> {
    return remember {
        List(count) {
            Particle(
                x       = (0..1000).random() / 1000f,
                y       = (0..1000).random() / 1000f,
                radius  = (3..8).random().toFloat(),
                color   = colors.random(),
                speedX  = ((1..4).random() * if ((0..1).random() == 0) 1f else -1f) / 2000f,
                speedY  = ((1..4).random() * if ((0..1).random() == 0) 1f else -1f) / 2000f,
                alpha   = (1..4).random() / 10f
            )
        }
    }
}

@Composable
fun ParticleBackground(
    modifier: Modifier = Modifier,
    particles: List<Particle> = rememberParticles()
) {
    val infiniteTransition = rememberInfiniteTransition(label = "particles")
    val tick by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue  = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(16000, easing = LinearEasing)
        ),
        label = "tick"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        particles.forEach { p ->
            val x = ((p.x + p.speedX * tick * 16000) % 1f + 1f) % 1f
            val y = ((p.y + p.speedY * tick * 16000) % 1f + 1f) % 1f
            drawCircle(
                color  = p.color.copy(alpha = p.alpha),
                radius = p.radius,
                center = Offset(x * w, y * h)
            )
        }
    }
}

@Composable
fun floatOffset(): Float {
    val transition = rememberInfiniteTransition(label = "float")
    val offset by transition.animateFloat(
        initialValue  = 0f,
        targetValue   = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatOffset"
    )
    return offset
}

@Composable
fun shimmerAlpha(): Float {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue  = 0.03f,
        targetValue   = 0.12f,
        animationSpec = infiniteRepeatable(
            animation  = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )
    return alpha
}

@Composable
fun AnimatedBackground(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        ParticleBackground()
        content()
    }
}