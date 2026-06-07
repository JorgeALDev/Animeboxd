package com.jorge.animeboxd.presentation.mylist

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.ui.theme.*

@Composable
fun MyListScreen(
    viewModel: MyListViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToCatalog: () -> Unit
) {
    val animes by viewModel.watchedAnimes.collectAsState()
    val watching = animes.filter { it.status.uppercase() == "WATCHING" }
    val completed = animes.filter { it.status.uppercase() == "COMPLETED" }
    val paused = animes.filter { it.status.uppercase() == "PAUSED" }

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
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "catálogo →",
                        style = MaterialTheme.typography.labelMedium.copy(color = TextMuted),
                        modifier = Modifier.clickable { onNavigateToCatalog() }
                    )
                    Text(
                        text = "← voltar",
                        style = MaterialTheme.typography.labelMedium.copy(color = VioletLight),
                        modifier = Modifier.clickable { onNavigateBack() }
                    )
                }
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            if (watching.isNotEmpty()) {
                item { SectionHead("Assistindo"); Spacer(Modifier.height(8.dp)) }
                items(watching, key = { "w_${it.id}" }) { entity ->
                    MyListCard(
                        entity = entity,
                        onStatus = { s -> viewModel.updateStatus(entity, s) },
                        onRemove = { viewModel.removeAnime(entity) }
                    )
                }
                item { Spacer(Modifier.height(8.dp)) }
            }

            if (completed.isNotEmpty()) {
                item { SectionHead("Concluídos"); Spacer(Modifier.height(8.dp)) }
                items(completed, key = { "c_${it.id}" }) { entity ->
                    MyListCard(
                        entity = entity,
                        onStatus = { s -> viewModel.updateStatus(entity, s) },
                        onRemove = { viewModel.removeAnime(entity) }
                    )
                }
                item { Spacer(Modifier.height(8.dp)) }
            }

            if (paused.isNotEmpty()) {
                item { SectionHead("Pausados"); Spacer(Modifier.height(8.dp)) }
                items(paused, key = { "p_${it.id}" }) { entity ->
                    MyListCard(
                        entity = entity,
                        onStatus = { s -> viewModel.updateStatus(entity, s) },
                        onRemove = { viewModel.removeAnime(entity) }
                    )
                }
                item { Spacer(Modifier.height(8.dp)) }
            }

            if (animes.isEmpty()) {
                item {
                    Spacer(Modifier.height(16.dp))
                    EmptyHint("Sua lista está vazia.\nAdicione animes pelo catálogo.")
                }
            }
        }
    }
}

@Composable
private fun MyListCard(
    entity: AnimeEntity,
    onStatus: (String) -> Unit,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AnimeThumb(url = entity.imageUrl, description = entity.title)

            Column(Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = entity.title,
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "remover",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextDim),
                        modifier = Modifier.clickable { onRemove() }
                    )
                }

                Text(
                    text = "${entity.genre} · ${entity.episodes} eps",
                    style = MaterialTheme.typography.labelSmall.copy(color = TextMuted),
                    modifier = Modifier.padding(vertical = 3.dp)
                )

                Text(
                    text = run {
                        val total = entity.episodes * entity.episodeDurationMin
                        "${total / 60}h ${total % 60}min"
                    },
                    style = MaterialTheme.typography.labelSmall.copy(color = TextDim),
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                StatusBadge(entity.status)

                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    StatusButton("WATCHING", "assistindo", entity.status, onStatus)
                    StatusButton("COMPLETED", "concluído", entity.status, onStatus)
                    StatusButton("PAUSED", "pausado", entity.status, onStatus)
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