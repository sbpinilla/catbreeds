package com.sergiodev.catbreeds.core.network

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Interfaz que define las operaciones para interactuar con un servicio web o una API.
 * @author sbpinilla
 * @version 1.0
 */
interface ApiClient {

    @GET("breeds")
    suspend fun getCats(): List<BreedsDTO>?

}