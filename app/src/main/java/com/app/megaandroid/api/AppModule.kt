package com.app.megaandroid.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Gustavo Martínez
 * @version 1.0.0
 * Object donde se crea la instancia de Retrofit.
 * Contiene la URL base de la API.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://fc0f3687fe6f.ngrok-free.app"

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }
}