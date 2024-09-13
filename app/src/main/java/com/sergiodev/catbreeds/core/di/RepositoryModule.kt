package com.sergiodev.catbreeds.core.di

import com.sergiodev.catbreeds.catList.repository.CatListRepository
import com.sergiodev.catbreeds.catList.repository.ICatListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Modulo que permite definir las injecciones de repositorio
 * @author sbpinilla
 * @version 1.0
 */
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideCatListRepository(authInteractor: CatListRepository): ICatListRepository


}