package com.jorge.anicatalog.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.anicatalog.data.local.AnimeEntity
import com.jorge.anicatalog.presentation.mylist.MyListViewModel
import com.jorge.anicatalog.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: MyListViewModel,
    onNavigateToCatalog: () -> Unit,
    onNavigateToMyList: () -> Unit
) {
    val animes by viewModel.watchedAnimes.collectAsState()
    val totalHours = viewModel.getTotalHours()
    val avgRating = if (animes.isEmpty()) "—" else "%.1f".format(animes.map { it.userRating }.average())
    val recentAnimes = animes.take(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = SurfaceBorder,
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 16.dp)
        ) {
            Text(
                text = "seu catálogo pessoal",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = TextMuted,
                    letterSpacing = 1.4.sp
                )
            )
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "Ani",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = TextSecondary,
                        fontWeight = FontWeight.Light
                    )
                )
                Text(
                    text = "Catalog",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                text = "onde cada história fica guardada",
                style = MaterialTheme.typography.labelMedium.copy(color = TextDim)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            StatsRow(
                stats = listOf(
                    animes.size.toString() to "animes",
                    "${totalHours}h" to "assistidas",
                    avgRating to "média"
                )
            )

            Spacer(Modifier.height(24.dp))

            SectionHead(label = "Recentes")
            Spacer(Modifier.height(12.dp))

            if (recentAnimes.isEmpty()) {
                EmptyHint("Adicione animes pelo catálogo\npara vê-los aqui.")
            } else {
                recentAnimes.forEach { entity ->
                    HomeAnimeCard(entity)
                    Spacer(Modifier.height(1.dp))
                }
            }

            Spacer(Modifier.height(24.dp))

            NoirButton(
                label = "Ver catálogo",
                filled = true,
                onClick = onNavigateToCatalog
            )
            Spacer(Modifier.height(8.dp))
            NoirButton(
                label = "Minha lista completa",
                filled = false,
                onClick = onNavigateToMyList
            )
        }

        BottomNav(current = "home", onHome = {}, onCatalog = onNavigateToCatalog, onList = onNavigateToMyList)
    }
}

@Composable
private fun HomeAnimeCard(entity: AnimeEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(OledBlack)
            .padding(vertical = 10.dp)
            .border(width = 0.dp, color = SurfaceBorder),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AnimeThumb(url = entity.imageUrl, description = entity.title)
        Column(Modifier.weight(1f)) {
            Text(
                text = entity.title,
                style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${entity.genre} · ${entity.episodes} eps",
                style = MaterialTheme.typography.labelSmall.copy(color = TextMuted),
                modifier = Modifier.padding(vertical = 3.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                StatusBadge(entity.status)
                Spacer(Modifier.weight(1f))
                Text(
                    text = "%.1f".format(entity.userRating),
                    style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
                )
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