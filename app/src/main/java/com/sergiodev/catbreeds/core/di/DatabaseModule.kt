package com.sergiodev.catbreeds.core.di

import android.content.Context
import androidx.room.Room
import com.sergiodev.catbreeds.core.database.CatbreedsDataBase
import com.sergiodev.catbreeds.core.database.dao.CatDAO

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideFinancialRecordDao(financialRecordDatabase: CatbreedsDataBase): CatDAO {
        return financialRecordDatabase.catDao()
    }

    @Provides
    @Singleton
    fun provideFinancialRecordDataBase(@ApplicationContext appContext: Context): CatbreedsDataBase {
        return Room.databaseBuilder(appContext, CatbreedsDataBase::class.java, "catbreedsDataBase").build()
    }

}