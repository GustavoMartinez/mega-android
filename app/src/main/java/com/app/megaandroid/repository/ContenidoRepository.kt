package com.app.megaandroid.repository

import com.app.megaandroid.api.ApiService
import com.app.megaandroid.model.Contenido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContenidoRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getContenidos(): List<Contenido> {
        return apiService.getContenidos()
    }

}