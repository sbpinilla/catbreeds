package com.sergiodev.catbreeds.catList.repository

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO

/**
 * Interfaz que define las operaciones del repositorio de catList.
 * @author sbpinilla
 * @version 1.0
 */
interface ICatListRepository {

    /** Metodo encargado de consulatr los gatos
     * @return Listado de gatos connsultado desde el web service
     */
    suspend fun getCats(): List<BreedsDTO>?

    /** Metodo encargado de guardar la lista de gatos en la bd local
     * @param cats Gatos a guardar de forma local
     */
    suspend fun saveCats(cats: List<BreedsDTO>)

    /** Metodod encargado de consulatr los gatos de la db local
     * @return Listado de gatos consultados desde la base de datos local
     */
    suspend fun getLocalCats(): List<BreedsDTO>

}