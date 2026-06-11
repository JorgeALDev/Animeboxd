package com.jorge.animeboxd.presentation.catalogo

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
fun CatalogoScreen(
    viewModel: CatalogoViewModel,
    onNavigateBack: () -> Unit
) {
    val busca by viewModel.busca.collectAsState()
    val animes = viewModel.animesFiltrados
    val idsSalvos by viewModel.idsSalvos.collectAsState()
    val estaCarregando by viewModel.estaCarregando.collectAsState()

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
                if (busca.isEmpty()) {
                    Text(
                        text = "buscar anime...",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextDim)
                    )
                }
                BasicTextField(
                    value = busca,
                    onValueChange = { viewModel.aoMudarBusca(it) },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodySmall.copy(color = TextPrimary),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (estaCarregando) {
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
                        EmptyHint("Nenhum anime encontrado\npara \"$busca\"")
                    }
                } else {
                    items(animes, key = { it.id }) { anime ->
                        CatalogoCard(
                            anime = anime,
                            estaAdicionado = anime.id in idsSalvos,
                            aoAdicionar = { viewModel.adicionarNaLista(anime) },
                            aoRemover = { viewModel.removerDaLista(anime) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CatalogoCard(
    anime: Anime,
    estaAdicionado: Boolean,
    aoAdicionar: () -> Unit,
    aoRemover: () -> Unit
) {
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
                            color = if (estaAdicionado) GreenDoneBorder else SurfaceBorder,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .background(
                            color = if (estaAdicionado) GreenDoneBg else OledBlack,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .clickable { if (estaAdicionado) aoRemover() else aoAdicionar() }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (estaAdicionado) "remover ✗" else "+ adicionar",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (estaAdicionado) GreenDone else TextSecondary,
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