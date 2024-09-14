package com.sergiodev.catbreeds.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Clase que representa la entidad CatEntity en la base de datos.
 * @author sbpinilla
 * @version 1.0
 */
@Entity
data class CatEntity(
    @PrimaryKey val id: String,
    val name: String,
    var url: String,
    val origin: String,
    val intelligence: Int,
    val description: String,
    val lifeSpan: String,
    val altNames: String,
    val urlWiki: String,
    val urlVetstreet: String
)
