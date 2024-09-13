package com.sergiodev.catbreeds.catList.repository

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO

/**
 * Interfaz que define las operaciones del repositorio de catList.
 * @author sbpinilla
 * @version 1.0
 */
interface ICatListRepository {

    /** Metodod encargado de consulatr los gatos
     * @return Listado de gatos connsultado desde el web service
     */
    suspend fun getCats(): List<BreedsDTO>?
}