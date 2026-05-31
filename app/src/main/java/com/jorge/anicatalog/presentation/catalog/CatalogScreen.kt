package com.jorge.anicatalog.presentation.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.anicatalog.domain.model.Anime
import com.jorge.anicatalog.ui.theme.*

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel,
    onNavigateBack: () -> Unit
) {
    val allAnimes = viewModel.catalogAnimes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 0.5.dp, color = SurfaceBorder, shape = RoundedCornerShape(0.dp))
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${allAnimes.size} títulos disponíveis",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = TextMuted,
                        letterSpacing = 1.4.sp
                    )
                )
                Text(
                    text = "← voltar",
                    style = MaterialTheme.typography.labelMedium.copy(color = TextMuted),
                    modifier = Modifier.clickable { onNavigateBack() }
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Catálogo",
                style = MaterialTheme.typography.headlineSmall.copy(color = White)
            )
            Text(
                text = "escolha seu próximo anime",
                style = MaterialTheme.typography.labelMedium.copy(color = TextDim)
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(allAnimes, key = { it.id }) { anime ->
                CatalogCard(
                    anime = anime,
                    onAdd = { viewModel.addToMyList(anime) }
                )
            }
        }

        BottomNav(current = "catalog", onHome = onNavigateBack, onCatalog = {}, onList = {})
    }
}

@Composable
private fun CatalogCard(anime: Anime, onAdd: () -> Unit) {
    var added by remember(anime.id) { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AnimeThumb(url = anime.imagemUrl, description = anime.titulo)

        Column(Modifier.weight(1f)) {
            Text(
                text = anime.titulo,
                style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${anime.genero} · ${anime.episodios} eps",
                style = MaterialTheme.typography.labelSmall.copy(color = TextMuted),
                modifier = Modifier.padding(vertical = 3.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "★ ${"%.1f".format(anime.nota)}",
                    style = MaterialTheme.typography.labelSmall.copy(color = AmberPaused)
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .border(
                            width = 0.5.dp,
                            color = if (added) GreenDoneBorder else SurfaceBorder,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .background(
                            color = if (added) GreenDoneBg else OledBlack,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .clickable(enabled = !added) {
                            onAdd()
                            added = true
                        }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (added) "adicionado ✓" else "+ adicionar",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (added) GreenDone else TextSecondary,
                            letterSpacing = 0.4.sp
                        )
                    )
                }
            }
        }
    }
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(SurfaceBorder)
    )
}