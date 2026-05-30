package com.jorge.anicatalog.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val avgRating = if (animes.isEmpty()) "—"
    else "%.1f".format(animes.map { it.userRating }.average())
    val recentAnimes = animes.take(3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperCream)
            .dotTexture()
            .spineDecoration()
            .pageCornerFold()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            HomeHeader()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                RuledLine()
                Spacer(Modifier.height(10.dp))

                StatsRow(
                    stats = listOf(
                        animes.size.toString() to "animes",
                        "${totalHours}h" to "assistidas",
                        avgRating to "média"
                    )
                )
                Spacer(Modifier.height(12.dp))

                SectionHead(label = "Recentes")
                Spacer(Modifier.height(8.dp))

                if (recentAnimes.isEmpty()) {
                    EmptyHint("Adicione animes pelo catálogo\npara vê-los aqui.")
                } else {
                    recentAnimes.forEach { entity ->
                        HomeAnimeCard(entity)
                    }
                }

                Spacer(Modifier.height(10.dp))

                PaperButton(
                    label = "Ver catálogo",
                    filled = true,
                    onClick = onNavigateToCatalog
                )
                Spacer(Modifier.height(5.dp))
                PaperButton(
                    label = "Minha lista completa",
                    filled = false,
                    onClick = onNavigateToMyList
                )
            }
        }

        PageNumber(
            page = 1,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 36.dp)
        )
    }
}

@Composable
private fun HomeHeader() {
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
        Text(
            text = "seu catálogo pessoal".uppercase(),
            style = MaterialTheme.typography.titleSmall.copy(
                color = InkMid,
                letterSpacing = 1.4.sp
            )
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "AniCatalog",
            style = MaterialTheme.typography.headlineMedium.copy(color = InkBlack)
        )
        Text(
            text = "onde cada história fica guardada",
            style = MaterialTheme.typography.bodySmall.copy(
                color = InkMid,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
private fun HomeAnimeCard(entity: com.jorge.anicatalog.data.local.AnimeEntity) {
    Row(
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
            .padding(vertical = 7.dp),
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
                text = "${entity.genre} · ${entity.episodes} eps",
                style = MaterialTheme.typography.bodySmall.copy(color = InkLight),
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                StatusChip(entity.status)
                Spacer(Modifier.weight(1f))
                Text(
                    text = "%.1f".format(entity.userRating),
                    style = MaterialTheme.typography.labelSmall.copy(color = InkLight)
                )
            }
        }
    }
}