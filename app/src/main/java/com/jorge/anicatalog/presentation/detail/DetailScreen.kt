package com.jorge.anicatalog.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jorge.anicatalog.data.local.AniCatalogDatabase
import com.jorge.anicatalog.data.repository.AnimeRepository
import com.jorge.anicatalog.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    animeId: Int,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val db = AniCatalogDatabase.getInstance(context)
    val repository = AnimeRepository(db.animeDao())
    val factory = remember(animeId, repository) {
        DetailViewModelFactory(animeId, repository)
    }
    val viewModel: DetailViewModel = viewModel(factory = factory)

    val anime by viewModel.anime.collectAsState()
    val isInMyList by viewModel.isInMyList.collectAsState()
    val userRating by viewModel.userRating.collectAsState()
    val userStatus by viewModel.userStatus.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(anime?.titulo ?: "Detalhes") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    if (isInMyList) {
                        IconButton(onClick = { viewModel.removeFromMyList() }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Remover da lista")
                        }
                    } else {
                        IconButton(onClick = { viewModel.addToMyList() }) {
                            Icon(Icons.Filled.Add, contentDescription = "Adicionar à lista")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (anime == null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(PaperCream)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(Color(0xFFE4DDD0))
                        .border(0.5.dp, PaperBorder)
                ) {
                    AsyncImage(
                        model = anime?.imagemUrl,
                        contentDescription = anime?.titulo,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = anime?.titulo ?: "",
                            style = MaterialTheme.typography.headlineSmall.copy(color = InkBlack),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = null,
                                tint = ScoreGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = "%.1f".format(anime?.nota ?: 0f),
                                style = MaterialTheme.typography.bodyMedium.copy(color = InkBlack)
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "${anime?.genero} · ${anime?.episodios} episódios",
                        style = MaterialTheme.typography.bodySmall.copy(color = InkMid)
                    )
                    Spacer(Modifier.height(12.dp))

                    if (isInMyList) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = InkRule,
                            thickness = 0.5.dp
                        )
                        Text(
                            text = "Seu progresso",
                            style = MaterialTheme.typography.titleSmall.copy(color = InkBlack)
                        )
                        Spacer(Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            StatusChip(userStatus)
                            Spacer(Modifier.weight(1f))
                            var localRating by remember(userRating) { mutableFloatStateOf(userRating) }
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Sua nota",
                                        style = MaterialTheme.typography.labelSmall.copy(color = InkLight)
                                    )
                                    Text(
                                        text = "%.1f".format(localRating),
                                        style = MaterialTheme.typography.labelSmall.copy(color = InkLight)
                                    )
                                }
                                Slider(
                                    value = localRating,
                                    onValueChange = { localRating = it },
                                    onValueChangeFinished = { viewModel.updateRating(localRating) },
                                    valueRange = 0f..10f,
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = SliderDefaults.colors(
                                        thumbColor = InkBlack,
                                        activeTrackColor = InkBlack,
                                        inactiveTrackColor = Color(0xFFDDDDDD)
                                    )
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            StatusButton("WATCHING", "assistindo", userStatus) { viewModel.updateStatus(it) }
                            StatusButton("COMPLETED", "concluído", userStatus) { viewModel.updateStatus(it) }
                            StatusButton("PAUSED", "pausado", userStatus) { viewModel.updateStatus(it) }
                        }
                        Spacer(Modifier.height(16.dp))
                    } else {
                        PaperButton(
                            label = "Adicionar à minha lista",
                            filled = true,
                            onClick = { viewModel.addToMyList() },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = InkRule,
                        thickness = 0.5.dp
                    )
                    Text(
                        text = "Sinopse",
                        style = MaterialTheme.typography.titleSmall.copy(color = InkBlack)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = anime?.sinopse ?: "Sem sinopse disponível.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = InkMid),
                        lineHeight = 20.sp
                    )
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}