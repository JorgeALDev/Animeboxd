package com.jorge.anicatalog.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PaperColorScheme = lightColorScheme(
    primary          = InkBlack,
    onPrimary        = PaperCream,
    background       = PaperCream,
    onBackground     = InkBlack,
    surface          = PaperLight,
    onSurface        = InkBlack,
    surfaceVariant   = PaperMid,
    onSurfaceVariant = InkMid,
    outline          = PaperBorder,
    outlineVariant   = InkFaint
)

@Composable
fun AniCatalogTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PaperColorScheme,
        typography  = PaperTypography,
        content     = content
    )
}