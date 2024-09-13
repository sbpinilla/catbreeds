package com.sergiodev.catbreeds.catList.ui.model

import java.io.Serializable

data class CatModel(
    val id: String,
    val name: String,
    val url: String,
    val origin: String,
    val intelligence: Int,
    val description: String,
    val lifeSpan: String,
    val altNames: String,
    val urlWiki: String,
    val urlVetstreet: String,
) : Serializable
