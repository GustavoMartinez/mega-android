package com.app.megaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.megaandroid.ui.screen.VideoScreen
import com.app.megaandroid.ui.theme.MegaAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Gustavo Martínez
 * @version 1.0.0
 * Clase principal de la aplicación.
 * Inicia la aplicación.
 * Inyecta la dependencia de la API.
 * Inicia el composable VideoScreen.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaAndroidTheme {
                VideoScreen()
            }
        }
    }
}

