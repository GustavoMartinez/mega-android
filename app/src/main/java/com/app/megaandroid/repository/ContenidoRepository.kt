package com.app.megaandroid.repository

import com.app.megaandroid.api.ApiService
import com.app.megaandroid.model.Contenido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContenidoRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getContenidos(): Result<List<Contenido>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getContenidos()
            }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}