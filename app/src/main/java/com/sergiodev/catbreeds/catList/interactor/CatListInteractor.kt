package com.sergiodev.catbreeds.catList.interactor

import com.sergiodev.catbreeds.catList.repository.ICatListRepository

import com.sergiodev.catbreeds.core.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * Implementación de [ICatListInteractor] que gestiona la interacción con el repositorio
 * @param catListRepository Repositorio de la lista de gatos
 * @author sbpinilla
 * @version 1.0
 */
class CatListInteractor @Inject constructor(private val catListRepository: ICatListRepository) : ICatListInteractor {

    override fun getCats() = flow {

        emit(ApiResponse.Loading(true))

        val response = catListRepository.getCats()?.map { x -> x.toCatModel() }

        if (response != null) {
            emit(ApiResponse.Success(response))
        } else {
            emit(ApiResponse.Error(""))
        }
        emit(ApiResponse.Loading(false))
    }.catch { e ->
        emit(ApiResponse.Loading(false))
        emit(ApiResponse.Error("Error -> ${e.message}"))
    }.flowOn(Dispatchers.IO)
}