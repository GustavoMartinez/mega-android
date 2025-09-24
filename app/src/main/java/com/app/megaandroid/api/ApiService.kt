package com.app.megaandroid.api

import com.app.megaandroid.model.Contenido
import retrofit2.http.GET

interface ApiService {
    @GET("/api/contenidos")
    suspend fun getContenidos(): List<Contenido>
}