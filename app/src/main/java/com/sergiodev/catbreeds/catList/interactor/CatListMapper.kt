package com.sergiodev.catbreeds.catList.interactor

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO
import com.sergiodev.catbreeds.catList.ui.model.CatModel

/**
 *  Metodo que nos permite convertir un [BreedsDTO] a [CatModel]
 */
fun BreedsDTO.toCatModel(): CatModel {
    return CatModel(
        id = this.id ?: "",
        name = this.name ?: "",
        origin = this.origin ?: "",
        intelligence = this.intelligence ?: 0,
        url = this.image?.url ?: "",
        description = this.description ?: "",
        lifeSpan = this.lifeSpan ?: "",
        altNames = this.altNames ?: "",
        urlVetstreet = this.urlVetstreet ?: "",
        urlWiki = this.urlWiki ?: "",
    )
}

