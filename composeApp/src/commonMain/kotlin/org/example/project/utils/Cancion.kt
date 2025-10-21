package org.example.project.utils

import org.jetbrains.compose.resources.DrawableResource

data class Cancion(
    val nombre: String,
    val duracion: Double,
    val imagen: DrawableResource,
    val asrtista: String,
    val activo: Boolean,
    val url: String? = null
)