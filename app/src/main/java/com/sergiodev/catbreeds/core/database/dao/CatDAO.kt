package com.sergiodev.catbreeds.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sergiodev.catbreeds.core.database.entity.CatEntity
import com.sergiodev.catbreeds.core.database.utils.BaseDAO

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad CatEntity.
 * @author sbpinilla
 * @version 1.0
 */
@Dao
interface CatDAO : BaseDAO<CatEntity> {

    @Query("Select * from CatEntity")
    fun getLocalCats(): List<CatEntity>
}