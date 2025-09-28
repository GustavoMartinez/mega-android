package com.app.megaandroid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.app.megaandroid.model.Contenido
import com.app.megaandroid.repository.ContenidoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Gustavo Martínez
 * Clase que contiene la lógica de la aplicación.
 * Obtiene los contenidos de la API y los muestra en la vista.
 * Inicia el reproductor de video.
 * Controla el cambio de video.
 */
@HiltViewModel
class ContenidosViewModel @Inject constructor (
    private val repository: ContenidoRepository
) : ViewModel() {
    private val _contenidos = MutableStateFlow<List<Contenido>>(emptyList())
    val contenidos: StateFlow<List<Contenido>> = _contenidos

    private var _player: ExoPlayer? = null
    val player get() = _player

    fun initPlayer(context: Context) {
        if (_player == null) {
            _player = ExoPlayer.Builder(context).build()
        }

    }

    fun playUrl(url: String) {
        _player?.apply {
            //stop()
            //clearMediaItems()
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        _player?.release()
        _player = null
    }

    init {
        fetchContenidos()
    }

    private fun fetchContenidos() {
        viewModelScope.launch {
            try {
                _contenidos.value = repository.getContenidos()
            }catch (e: Exception) {
                _contenidos.value = emptyList()
            }
        }
    }
}