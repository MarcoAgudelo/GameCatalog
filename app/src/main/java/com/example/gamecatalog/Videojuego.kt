package com.example.gamecatalog

import androidx.annotation.DrawableRes

data class Videojuego(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val descripcionCorta: String,
    val descripcionLarga: String,
    @DrawableRes val imagen: Int
)