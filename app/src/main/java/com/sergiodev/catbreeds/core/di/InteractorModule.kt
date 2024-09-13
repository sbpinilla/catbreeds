package com.sergiodev.catbreeds.core.di

import com.sergiodev.catbreeds.catList.interactor.CatListInteractor
import com.sergiodev.catbreeds.catList.interactor.ICatListInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/**
 * Modulo que permite definir las injecciones de Interactors
 * @author sbpinilla
 * @version 1.0
 */
@Module
@InstallIn(ViewModelComponent::class)
interface InteractorModule {

    @Binds
    fun provideCatListInteractor(catListInteractor: CatListInteractor): ICatListInteractor


}