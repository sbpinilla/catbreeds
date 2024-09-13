package com.sergiodev.catbreeds.catList.repository

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO
import com.sergiodev.catbreeds.core.network.ApiClient
import javax.inject.Inject


/**
 * Implementación de [ICatListRepository] que gestiona la interacción con fuentes de datos para los gatos
 * @param apiClient El servicio de API utilizado para operaciones de red
 * @author sbpinilla
 * @version 1.0
 */
class CatListRepository @Inject constructor(private val apiClient: ApiClient) : ICatListRepository {

    /**
     * {@inheritDoc}
     */
    override suspend fun getCats(): List<BreedsDTO>? = apiClient.getCats()

}