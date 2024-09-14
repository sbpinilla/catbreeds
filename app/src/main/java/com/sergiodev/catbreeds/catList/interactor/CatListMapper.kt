package com.sergiodev.catbreeds.catList.interactor

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO
import com.sergiodev.catbreeds.catList.interactor.dto.BreedsImageDTO
import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.core.database.entity.CatEntity

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

fun BreedsDTO.toCatEntity(): CatEntity {

    return CatEntity(
        id = this.id ?: "",
        name = this.name ?: "",
        origin = this.origin ?: "",
        intelligence = this.intelligence ?: 0,
        url = this.image?.url ?: "",
        description = this.description ?: "",
        lifeSpan = this.lifeSpan ?: "",
        altNames = this.altNames ?: "",
        urlVetstreet = this.urlVetstreet ?: "",
        urlWiki = this.urlWiki ?: ""
    )
}

fun CatEntity.toBreedsDTO(): BreedsDTO {

    return BreedsDTO().apply {
        id = this@toBreedsDTO.id
        name = this@toBreedsDTO.name
        origin = this@toBreedsDTO.origin
        intelligence = this@toBreedsDTO.intelligence
        image = BreedsImageDTO().apply { url = this@toBreedsDTO.url }
        description = this@toBreedsDTO.description
        lifeSpan = this@toBreedsDTO.lifeSpan
        altNames = this@toBreedsDTO.altNames
        urlVetstreet = this@toBreedsDTO.urlVetstreet
        urlWiki = this@toBreedsDTO.urlWiki
    }

}

