package com.jorge.animeboxd.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.presentation.minhaLista.MinhaListaViewModel
import com.jorge.animeboxd.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: MinhaListaViewModel,
    onNavigateToCatalog: () -> Unit,
    onNavigateToMyList: () -> Unit,
    onLogout: () -> Unit
) {
    val animes by viewModel.animesSalvos.collectAsState()
    val totalHoras = viewModel.obterTotalHoras()
    val totalMinutos = viewModel.obterMinutosRestantes()
    val animesRecentes = animes.take(3)

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
                    text = "seu catálogo pessoal",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = TextMuted,
                        letterSpacing = 1.4.sp
                    )
                )
                Text(
                    text = "Sair",
                    style = MaterialTheme.typography.labelMedium.copy(color = VioletLight),
                    modifier = Modifier.clickable { onLogout() }
                )
            }
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "Anime",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = VioletLight,
                        fontWeight = FontWeight.Light
                    )
                )
                Text(
                    text = "boxd",
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            StatsRow(
                stats = listOf(
                    animes.size.toString() to "animes na lista",
                    "${totalHoras}h ${totalMinutos}min" to "assistidos (concluídos)"
                )
            )

            Spacer(Modifier.height(24.dp))

            SectionHead(label = "Recentes")
            Spacer(Modifier.height(12.dp))

            if (animesRecentes.isEmpty()) {
                EmptyHint("Adicione animes pelo catálogo\npara vê-los aqui.")
            } else {
                animesRecentes.forEach { entity ->
                    HomeAnimeCard(entity)
                }
            }

            Spacer(Modifier.height(24.dp))

            AniButton(
                label = "Ver catálogo",
                filled = true,
                onClick = onNavigateToCatalog
            )
            Spacer(Modifier.height(8.dp))
            AniButton(
                label = "Minha lista completa",
                filled = false,
                onClick = onNavigateToMyList
            )
        }
    }
}

@Composable
private fun HomeAnimeCard(entity: AnimeEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
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
            StatusBadge(entity.status)
        }
    }
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(SurfaceBorder)
    )
}