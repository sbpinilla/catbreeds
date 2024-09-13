package com.sergiodev.catbreeds.catList.interactor

import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.core.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ICatListInteractor {

    fun getCats(): Flow<ApiResponse<List<CatModel>>>

}