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

        val response = catListRepository.getCats()

        if (response != null) {
            catListRepository.saveCats(response)
            val responseCatModel = response.map { x -> x.toCatModel() }
            emit(ApiResponse.Success(responseCatModel))
        } else {
            val responseLocal = catListRepository.getLocalCats().map { x -> x.toCatModel() }
            if (responseLocal.isNotEmpty()) {
                emit(ApiResponse.Success(responseLocal))
            } else {
                emit(ApiResponse.Error(""))
            }
        }
        emit(ApiResponse.Loading(false))
    }.catch { e ->

        val responseLocal = catListRepository.getLocalCats().map { x -> x.toCatModel() }
        if (responseLocal.isNotEmpty()) {
            emit(ApiResponse.Success(responseLocal))
        } else {
            emit(ApiResponse.Error("Error -> ${e.message}"))
        }

        emit(ApiResponse.Loading(false))

    }.flowOn(Dispatchers.IO)
}