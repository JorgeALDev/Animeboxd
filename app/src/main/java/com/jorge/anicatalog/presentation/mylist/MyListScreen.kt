package com.jorge.anicatalog.presentation.mylist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.anicatalog.data.local.AnimeEntity
import com.jorge.anicatalog.ui.theme.*

@Composable
fun MyListScreen(
    viewModel: MyListViewModel,
    onNavigateBack: () -> Unit
) {
    val animes by viewModel.watchedAnimes.collectAsState()

    val watching = animes.filter { it.status.uppercase() == "WATCHING" }
    val completed = animes.filter { it.status.uppercase() == "COMPLETED" }
    val paused = animes.filter { it.status.uppercase() == "PAUSED" }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperCream)
            .dotTexture()
            .spineDecoration()
            .pageCornerFold()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            MyListHeader(count = animes.size, onNavigateBack = onNavigateBack)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                item {
                    RuledLine()
                    Spacer(Modifier.height(10.dp))
                    StatsRow(
                        stats = listOf(
                            completed.size.toString() to "concluídos",
                            watching.size.toString() to "assistindo",
                            paused.size.toString() to "pausado"
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                }

                if (watching.isNotEmpty()) {
                    item {
                        SectionHead(label = "Assistindo")
                        Spacer(Modifier.height(8.dp))
                    }
                    items(watching, key = { "w_${it.id}" }) { entity ->
                        MyListCard(
                            entity = entity,
                            onRating = { r -> viewModel.updateRating(entity, r) },
                            onStatus = { s -> viewModel.updateStatus(entity, s) },
                            onRemove = { viewModel.removeAnime(entity) }
                        )
                    }
                    item { Spacer(Modifier.height(8.dp)) }
                }

                if (completed.isNotEmpty()) {
                    item {
                        SectionHead(label = "Concluídos")
                        Spacer(Modifier.height(8.dp))
                    }
                    items(completed, key = { "c_${it.id}" }) { entity ->
                        MyListCard(
                            entity = entity,
                            onRating = { r -> viewModel.updateRating(entity, r) },
                            onStatus = { s -> viewModel.updateStatus(entity, s) },
                            onRemove = { viewModel.removeAnime(entity) }
                        )
                    }
                    item { Spacer(Modifier.height(8.dp)) }
                }

                if (paused.isNotEmpty()) {
                    item {
                        SectionHead(label = "Pausados")
                        Spacer(Modifier.height(8.dp))
                    }
                    items(paused, key = { "p_${it.id}" }) { entity ->
                        MyListCard(
                            entity = entity,
                            onRating = { r -> viewModel.updateRating(entity, r) },
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

        PageNumber(
            page = 3,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 8.dp)
        )
    }
}

@Composable
private fun MyListHeader(count: Int, onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = InkBlack,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
            .padding(horizontal = 16.dp)
            .padding(top = 18.dp, bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$count títulos salvos".uppercase(),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = InkMid,
                    letterSpacing = 1.4.sp
                )
            )
            Text(
                text = "← voltar",
                style = MaterialTheme.typography.labelSmall.copy(color = InkMid),
                modifier = Modifier.clickable { onNavigateBack() }
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Minha lista",
            style = MaterialTheme.typography.headlineMedium.copy(color = InkBlack)
        )
        Text(
            text = "sua jornada até aqui",
            style = MaterialTheme.typography.bodySmall.copy(
                color = InkMid,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
private fun MyListCard(
    entity: AnimeEntity,
    onRating: (Float) -> Unit,
    onStatus: (String) -> Unit,
    onRemove: () -> Unit
) {
    var localRating by remember(entity.id) { mutableStateOf(entity.userRating) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = InkRule,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1f
                )
            }
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimeThumb(url = entity.imageUrl, description = entity.title)

            Column(Modifier.weight(1f)) {
                Text(
                    text = entity.title,
                    style = MaterialTheme.typography.bodyMedium.copy(color = InkBlack),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = entity.genre,
                    style = MaterialTheme.typography.bodySmall.copy(color = InkLight),
                    modifier = Modifier.padding(vertical = 2.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    StatusChip(entity.status)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "remover",
                        style = MaterialTheme.typography.labelSmall.copy(color = InkLight),
                        modifier = Modifier.clickable { onRemove() }
                    )
                }

                Spacer(Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "sua nota",
                        style = MaterialTheme.typography.labelSmall.copy(color = InkLight)
                    )
                    Text(
                        text = "%.1f".format(localRating),
                        style = MaterialTheme.typography.labelSmall.copy(color = InkLight)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Slider(
                    value = localRating,
                    onValueChange = { localRating = it },
                    onValueChangeFinished = { onRating(localRating) },
                    valueRange = 0f..10f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = InkBlack,
                        activeTrackColor = InkBlack,
                        inactiveTrackColor = Color(0xFFDDDDDD),
                        activeTickColor = Color.Transparent,
                        inactiveTickColor = Color.Transparent
                    )
                )

                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    StatusButton("WATCHING", "assistindo", entity.status, onStatus)
                    StatusButton("COMPLETED", "concluído", entity.status, onStatus)
                    StatusButton("PAUSED", "pausado", entity.status, onStatus)
                }
            }
        }
    }
}