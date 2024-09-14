package com.sergiodev.catbreeds.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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
