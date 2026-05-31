package com.jorge.anicatalog.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(SurfaceCard, RoundedCornerShape(8.dp))
            .border(0.5.dp, SurfaceBorder, RoundedCornerShape(8.dp))
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = White,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Light
            )
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                color = TextDim,
                letterSpacing = 0.8.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StatsRow(
    stats: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        stats.forEach { (value, label) ->
            StatBox(
                value = value,
                label = label,
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
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stats.forEach { (value, label) ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 0.dp,
                        color = Color.Transparent
                    )
                    .padding(bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = White,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Light
                        )
                    )
                }
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(SurfaceBorder)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = label.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextDim,
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
            .width(48.dp)
            .height(68.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(SurfaceHigh)
            .border(0.5.dp, SurfaceBorder, RoundedCornerShape(4.dp))
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
    val (label, textColor, bgColor, borderColor, dashed) = when (status.uppercase()) {
        "COMPLETED" -> StatusStyle("concluído",  GreenDone,   GreenDoneBg, GreenDoneBorder, false)
        "WATCHING"  -> StatusStyle("assistindo", BluePrimary, BlueBg,      BlueBorder,      true)
        "PAUSED"    -> StatusStyle("pausado",    AmberPaused, AmberBg,     AmberBorder,     true)
        else        -> StatusStyle(status.lowercase(), TextSecondary, SurfaceCard, SurfaceBorder, false)
    }

    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(3.dp))
            .then(
                if (dashed) Modifier.dashedBorder(borderColor)
                else Modifier.border(0.5.dp, borderColor, RoundedCornerShape(3.dp))
            )
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = textColor,
                letterSpacing = 0.4.sp
            )
        )
    }
}

private data class StatusStyle(
    val label: String,
    val textColor: Color,
    val bgColor: Color,
    val borderColor: Color,
    val dashed: Boolean
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
            .border(
                width = if (isActive) 0.5.dp else 0.5.dp,
                color = if (isActive) TextSecondary else SurfaceBorder,
                shape = RoundedCornerShape(3.dp)
            )
            .background(
                if (isActive) SurfaceHigh else Color.Transparent,
                RoundedCornerShape(3.dp)
            )
            .clickable { onStatus(value) }
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (isActive) TextPrimary else TextMuted
            )
        )
    }
}

@Composable
fun NoirButton(
    label: String,
    filled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(if (filled) White else Color.Transparent)
            .border(
                width = if (filled) 0.dp else 0.5.dp,
                color = if (filled) Color.Transparent else SurfaceBorder,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = if (filled) Black else TextMuted,
                letterSpacing = 1.0.sp
            )
        )
    }
}

@Composable
fun EmptyHint(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(0.5.dp, SurfaceBorder, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextMuted,
                fontStyle = FontStyle.Italic,
                lineHeight = 20.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}