package com.jorge.anicatalog.presentation.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorge.anicatalog.domain.model.Anime
import com.jorge.anicatalog.ui.theme.*

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel,
    onNavigateToMyList: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val allAnimes = viewModel.catalogAnimes

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperCream)
            .dotTexture()
            .spineDecoration()
            .pageCornerFold()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            CatalogHeader(
                count = allAnimes.size,
                onNavigateBack = onNavigateBack
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                item {
                    RuledLine()
                    Spacer(Modifier.height(8.dp))
                }

                items(allAnimes, key = { it.id }) { anime ->
                    CatalogCard(
                        anime = anime,
                        onAdd = {
                            viewModel.addToMyList(anime)
                            onNavigateToMyList()
                        }
                    )
                }

                item { Spacer(Modifier.height(8.dp)) }
            }
        }

        PageNumber(
            page = 2,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 8.dp)
        )
    }
}

@Composable
private fun CatalogHeader(count: Int, onNavigateBack: () -> Unit) {
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
                text = "$count títulos disponíveis".uppercase(),
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
            text = "Catálogo",
            style = MaterialTheme.typography.headlineMedium.copy(color = InkBlack)
        )
        Text(
            text = "escolha seu próximo anime",
            style = MaterialTheme.typography.bodySmall.copy(
                color = InkMid,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
private fun CatalogCard(
    anime: Anime,
    onAdd: () -> Unit
) {
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
        AnimeThumb(url = anime.imagemUrl, description = anime.titulo)

        Column(Modifier.weight(1f)) {
            Text(
                text = anime.titulo,
                style = MaterialTheme.typography.bodyMedium.copy(color = InkBlack),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${anime.genero} · ${anime.episodios} eps",
                style = MaterialTheme.typography.bodySmall.copy(color = InkLight),
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "★ ${"%.1f".format(anime.nota)}",
                    style = MaterialTheme.typography.labelSmall.copy(color = ScoreGold)
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .border(0.5.dp, InkBlack)
                        .clickable { onAdd() }
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "+ adicionar",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = InkBlack,
                            letterSpacing = 0.4.sp
                        )
                    )
                }
            }
        }
    }
}