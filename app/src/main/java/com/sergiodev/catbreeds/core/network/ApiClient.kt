package com.sergiodev.catbreeds.core.network

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {

    @GET("breeds")
    suspend fun getCats(): List<BreedsDTO>?

}