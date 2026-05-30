package com.jorge.anicatalog.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun RuledLine(modifier: Modifier = Modifier) {
    Spacer(
        modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(InkRule)
    )
}

@Composable
fun SectionHead(label: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.titleSmall.copy(
                color = InkLight,
                letterSpacing = 1.4.sp,
                fontFamily = FontFamily.SansSerif
            )
        )
        Spacer(Modifier.width(8.dp))
        Spacer(
            Modifier
                .weight(1f)
                .height(0.5.dp)
                .background(InkFaint)
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
        modifier = modifier.padding(vertical = 12.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.displayMedium.copy(
                color = InkBlack,
                letterSpacing = (-0.5).sp
            )
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                color = InkLight,
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
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawRect(
                    color = InkBlack,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                )
                val w = size.width / stats.size
                for (i in 1 until stats.size) {
                    drawLine(
                        color = InkBlack,
                        start = Offset(w * i, 0f),
                        end = Offset(w * i, size.height),
                        strokeWidth = 2f
                    )
                }
            }
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
fun PageNumber(page: Int, modifier: Modifier = Modifier) {
    Text(
        text = "p. $page",
        modifier = modifier,
        style = MaterialTheme.typography.labelSmall.copy(
            color = InkFaint,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif
        )
    )
}

@Composable
fun AnimeThumb(url: String, description: String) {
    Box(
        modifier = Modifier
            .width(52.dp)
            .height(72.dp)
            .background(Color(0xFFE4DDD0))
            .border(0.5.dp, PaperBorder)
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
fun StatusChip(status: String) {
    val label = when (status.uppercase()) {
        "COMPLETED" -> "concluído"
        "WATCHING"  -> "assistindo"
        "PAUSED"    -> "pausado"
        else        -> status.lowercase()
    }
    val isDashed = status.uppercase() != "COMPLETED"
    val color = when (status.uppercase()) {
        "COMPLETED" -> InkBlack
        "WATCHING"  -> InkMid
        else        -> InkLight
    }
    val bgColor = if (status.uppercase() == "COMPLETED") PaperMid else Color.Transparent

    Box(
        modifier = Modifier
            .background(bgColor)
            .then(
                if (isDashed) Modifier.dashedBorder(color = color)
                else Modifier.border(width = 0.5.dp, color = color)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = color,
                letterSpacing = 0.4.sp
            )
        )
    }
}

@Composable
fun PaperButton(
    label: String,
    filled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.5.dp, InkBlack)
            .background(if (filled) InkBlack else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = if (filled) PaperCream else InkBlack,
                letterSpacing = 1.2.sp
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
            .border(0.5.dp, InkFaint),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = InkLight,
                fontStyle = FontStyle.Italic,
                lineHeight = 20.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

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
                width = if (isActive) 1.dp else 0.5.dp,
                color = if (isActive) InkBlack else InkLight
            )
            .background(if (isActive) PaperMid else Color.Transparent)
            .clickable { onStatus(value) }
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (isActive) InkBlack else InkLight,
                letterSpacing = 0.4.sp
            )
        )
    }
}