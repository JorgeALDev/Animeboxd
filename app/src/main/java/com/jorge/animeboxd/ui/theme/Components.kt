package com.jorge.animeboxd.ui.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf

@Composable
fun SectionHead(label: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                color = TextMuted,
                letterSpacing = 1.4.sp
            )
        )
        Spacer(Modifier.width(8.dp))
        Spacer(
            Modifier
                .weight(1f)
                .height(0.5.dp)
                .background(SurfaceBorder)
        )
    }
}

@Composable
fun StatBox(
    value: String,
    label: String,
    accentColor: Color = VioletLight,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        accentColor.copy(alpha = 0.12f),
                        accentColor.copy(alpha = 0.04f)
                    )
                )
            )
            .border(0.5.dp, accentColor.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = White,
                    fontWeight = FontWeight.Light
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = accentColor.copy(alpha = 0.6f),
                    letterSpacing = 0.8.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun StatsRow(
    stats: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    val colors = listOf(VioletLight, GreenDone)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stats.forEachIndexed { i, (value, label) ->
            StatBox(
                value = value,
                label = label,
                accentColor = colors[i % colors.size],
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun MiniStatsRow(
    stats: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    val barColors = listOf(GreenDone, VioletLight, AmberPaused)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stats.forEachIndexed { i, (value, label) ->
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = White,
                        fontWeight = FontWeight.Light
                    )
                )
                Spacer(Modifier.height(6.dp))
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    barColors[i % barColors.size].copy(alpha = 0.6f),
                                    Color.Transparent
                                )
                            )
                        )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = label.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextMuted,
                        letterSpacing = 0.8.sp
                    )
                )
            }
        }
    }
}

@Composable
fun AnimeThumb(url: String, description: String) {
    Box(
        modifier = Modifier
            .width(56.dp)
            .height(78.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceHigh)
            .border(0.5.dp, SurfaceBorder, RoundedCornerShape(8.dp))
    ) {
        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val (label, textColor, bgColor, borderColor) = when (status.uppercase()) {
        "COMPLETED" -> StatusStyle("concluído", GreenDone, GreenDoneBg, GreenDoneBorder)
        "WATCHING"  -> StatusStyle("assistindo", VioletLight, VioletDark.copy(alpha = 0.3f), VioletLight)
        "PAUSED"    -> StatusStyle("pausado", AmberPaused, AmberBg, AmberBorder)
        else        -> StatusStyle(status.lowercase(), TextSecondary, SurfaceCard, SurfaceBorder)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .border(0.5.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = textColor,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.4.sp
            )
        )
    }
}

private data class StatusStyle(
    val label: String, val textColor: Color, val bgColor: Color, val borderColor: Color
)

@Composable
fun StatusButton(
    value: String,
    label: String,
    current: String,
    onStatus: (String) -> Unit
) {
    val isActive = current.uppercase() == value
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isActive) VioletPrimary.copy(alpha = 0.15f) else Color.Transparent
            )
            .border(
                width = 0.5.dp,
                color = if (isActive) VioletLight.copy(alpha = 0.5f) else SurfaceBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onStatus(value) }
            .padding(horizontal = 12.dp, vertical = 5.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (isActive) VioletLight else TextMuted
            )
        )
    }
}

@Composable
fun StatusButtonSmall(
    value: String,
    label: String,
    current: String,
    onStatus: (String) -> Unit
) {
    val isActive = current.uppercase() == value
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isActive) VioletPrimary.copy(alpha = 0.2f) else Color.Transparent
            )
            .border(
                width = 0.5.dp,
                color = if (isActive) VioletLight.copy(alpha = 0.6f) else SurfaceBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onStatus(value) }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                color = if (isActive) VioletLight else TextMuted
            )
        )
    }
}

@Composable
fun StarRating(rating: Float, onRatingChange: (Float) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        for (i in 1..5) {
            val icon: ImageVector = when {
                rating >= i -> Icons.Default.Star
                rating >= i - 0.5f -> Icons.Default.StarHalf
                else -> Icons.Default.StarBorder
            }
            Icon(
                imageVector = icon,
                contentDescription = "Estrela $i",
                tint = if (rating >= i - 0.5f) AmberPaused else TextMuted,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onRatingChange(i.toFloat()) }
            )
        }
    }
}

@Composable
fun EpisodeProgress(
    watched: Int,
    total: Int,
    onProgressChange: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$watched/$total eps",
            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
        )
        if (watched < total) {
            Text(
                text = "+",
                style = MaterialTheme.typography.labelSmall.copy(color = VioletLight),
                modifier = Modifier
                    .clickable { onProgressChange(watched + 1) }
                    .padding(horizontal = 4.dp)
            )
        }
        if (watched > 0) {
            Text(
                text = "-",
                style = MaterialTheme.typography.labelSmall.copy(color = TextDim),
                modifier = Modifier
                    .clickable { onProgressChange(watched - 1) }
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun AniButton(
    label: String,
    filled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.96f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "btnScale"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clip(RoundedCornerShape(10.dp))
            .then(
                if (filled) Modifier.background(
                    Brush.horizontalGradient(listOf(VioletPrimary, VioletLight))
                ) else Modifier
                    .background(Color.Transparent)
                    .border(0.5.dp, SurfaceBorder, RoundedCornerShape(10.dp))
            )
            .clickable {
                pressed = true
                onClick()
            }
            .padding(vertical = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = if (filled) White else TextMuted,
                letterSpacing = 1.0.sp
            )
        )
    }

    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(100)
            pressed = false
        }
    }
}

@Composable
fun EmptyHint(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .border(0.5.dp, SurfaceBorder, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextMuted,
                fontStyle = FontStyle.Italic,
                lineHeight = 22.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}