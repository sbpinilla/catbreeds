package com.sergiodev.catbreeds.catList.interactor

import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.core.network.ApiResponse
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz que define las operaciones del interactor de CatList
 * Esta clase es el puente entre el view model y el repositorio.
 * @author sbpinilla
 * @version 1.0
 */
interface ICatListInteractor {

    /** Metodo encargado de consulatr los gatos
     * @return Listado de gaatos
     */
    fun getCats(): Flow<ApiResponse<List<CatModel>>>

}