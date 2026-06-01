package com.jorge.animeboxd.presentation.mylist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.ui.theme.*

@Composable
fun MyListScreen(
    viewModel: MyListViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onAnimeClick: (Int) -> Unit
) {
    val animes by viewModel.watchedAnimes.collectAsState()

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
                    text = "${animes.size} títulos salvos",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = TextMuted,
                        letterSpacing = 1.4.sp
                    )
                )
                // Apenas botão voltar estilizado (link removido)
                Text(
                    text = "← voltar",
                    style = MaterialTheme.typography.labelMedium.copy(color = VioletLight),
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(VioletDark.copy(alpha = 0.2f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .clickable { onNavigateBack() }
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Minha lista",
                style = MaterialTheme.typography.headlineSmall.copy(color = White)
            )
            Text(
                text = "sua jornada até aqui",
                style = MaterialTheme.typography.labelMedium.copy(color = TextDim)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(animes, key = { it.id }) { entity ->
                MyListGridCard(
                    entity = entity,
                    onStatus = { s -> viewModel.updateStatus(entity, s) },
                    onRemove = { viewModel.removeAnime(entity) },
                    onRatingChange = { r -> viewModel.updateRating(entity, r) },
                    onProgressChange = { p -> viewModel.updateWatchedEpisodes(entity, p) },
                    onClick = { onAnimeClick(entity.id) }
                )
            }
            if (animes.isEmpty()) {
                item {
                    EmptyHint("Sua lista está vazia.\nAdicione animes pelo catálogo.")
                }
            }
        }
    }
}

@Composable
private fun MyListGridCard(
    entity: AnimeEntity,
    onStatus: (String) -> Unit,
    onRemove: () -> Unit,
    onRatingChange: (Float) -> Unit,
    onProgressChange: (Int) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceCard)
            .border(0.5.dp, SurfaceBorder, RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        ) {
            AsyncImage(
                model = entity.imageUrl,
                contentDescription = entity.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VioletDark.copy(alpha = 0.7f))
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                StatusBadge(entity.status)
            }
        }
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = entity.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextPrimary,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${entity.genre} · ${entity.episodes} eps",
                style = MaterialTheme.typography.labelSmall.copy(color = TextMuted),
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Spacer(Modifier.height(4.dp))
            StarRating(
                rating = entity.rating,
                onRatingChange = onRatingChange
            )
            Spacer(Modifier.height(6.dp))
            EpisodeProgress(
                watched = entity.watchedEpisodes,
                total = entity.episodes,
                onProgressChange = onProgressChange
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatusButtonSmall("WATCHING", "assistindo", entity.status, onStatus)
                StatusButtonSmall("COMPLETED", "concluído", entity.status, onStatus)
                StatusButtonSmall("PAUSED", "pausado", entity.status, onStatus)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "remover",
                style = MaterialTheme.typography.labelSmall.copy(color = TextDim),
                modifier = Modifier
                    .clickable { onRemove() }
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}