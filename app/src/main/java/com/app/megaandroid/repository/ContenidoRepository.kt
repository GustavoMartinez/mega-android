package com.app.megaandroid.repository

import com.app.megaandroid.api.ApiService
import com.app.megaandroid.model.Contenido

class ContenidoRepository(private val api: ApiService) {
    suspend fun obtenerContenidos(): List<Contenido> {
        return api.getContenidos()
    }
}