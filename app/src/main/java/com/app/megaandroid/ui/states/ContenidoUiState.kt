package com.app.megaandroid.ui.states

import com.app.megaandroid.model.Contenido

sealed class ContenidoUiState {
    object Loading : ContenidoUiState()
    data class Success(val contenidos: List<Contenido>) : ContenidoUiState()
    data class Error(val message: String) : ContenidoUiState()

}