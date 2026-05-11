package com.jorge.anicatalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun HomeScreen(onSair: () -> Unit) {
    Column(Modifier
        .fillMaxSize()
        .padding(top = 16.dp)) {

        Button(
            onClick = onSair,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Sair")
        }

        Text(
            text = "Catálogo de Animes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(animes) { anime ->
                AnimeCard(anime)
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(Modifier.padding(8.dp)) {

            AsyncImage(
                model = anime.imagemUrl,
                contentDescription = anime.titulo,
                modifier = Modifier.size(100.dp)
            )

            Column(Modifier.padding(start = 12.dp)) {
                Text(anime.titulo, style = MaterialTheme.typography.titleMedium)
                Text(anime.genero)
                Text("⭐ ${anime.nota}")
                Text(anime.sinopse, maxLines = 3)
            }
        }
    }
}