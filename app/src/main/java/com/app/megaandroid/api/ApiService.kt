package com.app.megaandroid.api

import com.app.megaandroid.model.Contenido
import retrofit2.http.GET

/**
 * @author Gustavo Martínez
 * @version 1.0.0
 * Interface que contiene la petición GET a la API.
 * Obtiene los contenidos de la programación.
 */
interface ApiService {
    @GET("api/contenidos")
    suspend fun getContenidos(): List<Contenido>
}