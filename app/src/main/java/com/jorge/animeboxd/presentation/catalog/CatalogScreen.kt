package com.jorge.animeboxd.presentation.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.animeboxd.domain.model.Anime
import com.jorge.animeboxd.ui.theme.*

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel,
    onNavigateBack: () -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val animes = viewModel.filteredAnimes
    val savedIds by viewModel.savedIds.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

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
                    text = "${animes.size} títulos",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = TextMuted,
                        letterSpacing = 1.4.sp
                    )
                )
                Text(
                    text = "← voltar",
                    style = MaterialTheme.typography.labelMedium.copy(color = VioletLight),
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

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp, SurfaceBorder, RoundedCornerShape(6.dp))
                    .background(SurfaceCard, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                if (searchQuery.isEmpty()) {
                    Text(
                        text = "buscar anime...",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextDim)
                    )
                }
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.onSearchChange(it) },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodySmall.copy(color = TextPrimary),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = VioletLight)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                if (animes.isEmpty()) {
                    item {
                        Spacer(Modifier.height(32.dp))
                        EmptyHint("Nenhum anime encontrado\npara \"$searchQuery\"")
                    }
                } else {
                    items(animes, key = { it.id }) { anime ->
                        CatalogCard(
                            anime = anime,
                            isAdded = anime.id in savedIds,
                            onAdd = { viewModel.addToMyList(anime) },
                            onRemove = { viewModel.removeFromMyList(anime) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CatalogCard(anime: Anime, isAdded: Boolean, onAdd: () -> Unit, onRemove: () -> Unit) {
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
                    text = "${anime.episodios * anime.duracaoEpMin / 60}h ${anime.episodios * anime.duracaoEpMin % 60}min",
                    style = MaterialTheme.typography.labelSmall.copy(color = TextDim)
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .border(
                            width = 0.5.dp,
                            color = if (isAdded) GreenDoneBorder else SurfaceBorder,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .background(
                            color = if (isAdded) GreenDoneBg else OledBlack,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .clickable { if (isAdded) onRemove() else onAdd() }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (isAdded) "remover ✗" else "+ adicionar",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (isAdded) GreenDone else TextSecondary,
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