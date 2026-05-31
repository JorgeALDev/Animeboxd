package com.jorge.anicatalog.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNav(
    current: String,
    onHome: () -> Unit,
    onCatalog: () -> Unit,
    onList: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceCard)
            .border(width = 0.5.dp, color = SurfaceBorder)
            .navigationBarsPadding()
    ) {
        listOf(
            Triple("home",    "Início",   onHome),
            Triple("catalog", "Catálogo", onCatalog),
            Triple("mylist",  "Lista",    onList)
        ).forEach { (key, label, action) ->
            val isActive = current == key
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable(enabled = !isActive) { action() }
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = if (isActive) White else TextDim,
                        letterSpacing = 0.8.sp
                    )
                )
            }
        }
    }
}