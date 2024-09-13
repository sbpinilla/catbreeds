package com.sergiodev.catbreeds.core.network

/**
 * Clase  que representa las diferentes respuestas de una solicitud a una API o servicio web.
 * @author sbpinilla
 * @version 1.0
 */
sealed class ApiResponse<out T> {
    data class Loading(val isLoading: Boolean) : ApiResponse<Nothing>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
}