package com.app.megaandroid.ui.screen

import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.app.megaandroid.model.Contenido
import com.app.megaandroid.viewmodel.ContenidosViewModel

/**
 * @author Gustavo Martínez
 * @version 1.0.0
 * File que contiene la vista principal de la aplicación.
 * Usa dos composables:
 * - VideoScreen: Composable que contiene la vista principal de la aplicación.
 * - ContenidoItem: Composable que muestra un elemento de contenido.
 */
@OptIn(UnstableApi::class)
@Composable
fun VideoScreen(viewModel: ContenidosViewModel = hiltViewModel()
) {

    val contenidos by viewModel.contenidos.collectAsStateWithLifecycle()
    var selectedUrl by remember { mutableStateOf<String?>(null) }
    var playerView by remember { mutableStateOf<PlayerView?>(null) }

    if (contenidos.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.initPlayer(context)
        }

        LaunchedEffect(playerView, selectedUrl) {
            val pv = playerView ?: return@LaunchedEffect
            val player = viewModel.player ?: return@LaunchedEffect
            pv.player = player
            selectedUrl?.let {viewModel.playUrl(it)}
        }

        Column(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        useController = true
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        keepScreenOn = true
                        playerView = this
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(contenidos) { contenido ->
                    ContenidoItem(
                        contenido = contenido,
                        onClick = { selectedUrl = contenido.url }
                        )

                }
            }
        }

    }
}

@Composable
fun ContenidoItem(
    contenido: Contenido,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
        ) {
            AsyncImage(
                model = contenido.imagen,
                contentDescription = contenido.titulo,
                modifier = Modifier
                    .size(height = 90.dp, width = 120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = contenido.titulo,
                    style = MaterialTheme.typography.titleMedium)
                Text(text = contenido.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }

}