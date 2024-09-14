package com.sergiodev.catbreeds.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sergiodev.catbreeds.core.database.entity.CatEntity
import com.sergiodev.catbreeds.core.database.utils.BaseDAO

@Dao
interface CatDAO : BaseDAO<CatEntity> {

    @Query("Select * from CatEntity")
    fun getLocalCats(): List<CatEntity>
}