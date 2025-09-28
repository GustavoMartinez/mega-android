package com.app.megaandroid.model

/**
 * @author Gustavo Martínez
 * Data class que contiene el modelo de la tabla contenidos de la base de datos
 */
data class Contenido(
    var id:Int,
    var titulo:String,
    var descripcion:String,
    var imagen:String,
    var url:String)
