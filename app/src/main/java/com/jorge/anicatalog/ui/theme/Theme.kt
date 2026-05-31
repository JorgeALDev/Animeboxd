package com.jorge.anicatalog.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val NoirColorScheme = darkColorScheme(
    primary          = White,
    onPrimary        = Black,
    background       = OledBlack,
    onBackground     = TextPrimary,
    surface          = SurfaceCard,
    onSurface        = TextPrimary,
    surfaceVariant   = SurfaceHigh,
    onSurfaceVariant = TextSecondary,
    outline          = SurfaceBorder,
    outlineVariant   = TextDim
)

@Composable
fun AniCatalogTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NoirColorScheme,
        typography  = NoirTypography,
        content     = content
    )
}