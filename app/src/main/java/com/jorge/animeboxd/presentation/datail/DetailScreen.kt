package com.jorge.animeboxd.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.ui.theme.*
import com.jorge.animeboxd.presentation.mylist.MyListViewModel
import kotlin.math.min
import kotlin.math.max

@Composable
fun DetailScreen(
    viewModel: MyListViewModel,
    anime: AnimeEntity,
    onNavigateBack: () -> Unit
) {
    var localRating by remember(anime.rating) { mutableStateOf(anime.rating) }
    var localWatched by remember(anime.watchedEpisodes) { mutableStateOf(anime.watchedEpisodes) }
    var localStatus by remember(anime.status) { mutableStateOf(anime.status) }

    LaunchedEffect(localRating) {
        if (localRating != anime.rating) {
            viewModel.updateRating(anime, localRating)
        }
    }
    LaunchedEffect(localWatched) {
        if (localWatched != anime.watchedEpisodes) {
            viewModel.updateWatchedEpisodes(anime, localWatched)
        }
    }
    LaunchedEffect(localStatus) {
        if (localStatus != anime.status) {
            viewModel.updateStatus(anime, localStatus)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        ) {
            AsyncImage(
                model = anime.imageUrl,
                contentDescription = anime.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Brush.verticalGradient(listOf(OledBlack.copy(alpha = 0.7f), Color.Transparent)))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = anime.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "${anime.genre} • ${anime.episodes} episódios",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextMuted),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "Duração total: ${anime.episodes * anime.episodeDurationMin / 60}h ${anime.episodes * anime.episodeDurationMin % 60}min",
                style = MaterialTheme.typography.bodySmall.copy(color = TextDim),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            DetailSection(title = "Sua avaliação") {
                StarRating(rating = localRating) { newRating ->
                    localRating = newRating
                }
            }

            Spacer(Modifier.height(16.dp))

            DetailSection(title = "Progresso") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = localWatched.toString(),
                        onValueChange = {
                            val new = it.toIntOrNull()
                            if (new != null && new in 0..anime.episodes) {
                                localWatched = new
                            }
                        },
                        modifier = Modifier.width(80.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodySmall.copy(color = TextPrimary),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VioletLight,
                            unfocusedBorderColor = SurfaceBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "de ${anime.episodes} episódios",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
                    )
                    Spacer(Modifier.width(16.dp))
                    Row {
                        if (localWatched < anime.episodes) {
                            Text(
                                text = "+10",
                                style = MaterialTheme.typography.bodySmall.copy(color = VioletLight),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(VioletDark.copy(alpha = 0.2f))
                                    .clickable { localWatched = min(localWatched + 10, anime.episodes) }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        if (localWatched > 0) {
                            Text(
                                text = "-10",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextDim),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceCard)
                                    .clickable { localWatched = max(localWatched - 10, 0) }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            DetailSection(title = "Status") {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatusButton("WATCHING", "Assistindo", localStatus) { localStatus = it }
                    StatusButton("COMPLETED", "Concluído", localStatus) { localStatus = it }
                    StatusButton("PAUSED", "Pausado", localStatus) { localStatus = it }
                }
            }

            Spacer(Modifier.height(24.dp))

            AniButton(
                label = "Remover da minha lista",
                filled = false,
                onClick = {
                    viewModel.removeAnime(anime)
                    onNavigateBack()
                }
            )
            Spacer(Modifier.height(12.dp))
            AniButton(
                label = "Voltar",
                filled = true,
                onClick = onNavigateBack
            )
        }
    }
}

@Composable
fun DetailSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = VioletLight,
                letterSpacing = 0.8.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}