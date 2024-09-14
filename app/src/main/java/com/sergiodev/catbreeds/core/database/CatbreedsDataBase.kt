package com.sergiodev.catbreeds.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sergiodev.catbreeds.core.database.dao.CatDAO
import com.sergiodev.catbreeds.core.database.entity.CatEntity

@Database(entities = [CatEntity::class], version = 1)

abstract class CatbreedsDataBase : RoomDatabase() {

    abstract fun catDao(): CatDAO
}

