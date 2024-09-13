package com.sergiodev.catbreeds.catList.interactor.dto

import com.google.gson.annotations.SerializedName


class BreedsDTO {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("origin")
    var origin: String? = null

    @SerializedName("intelligence")
    var intelligence: Int? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("life_span")
    var lifeSpan: String? = null

    @SerializedName("alt_names")
    var altNames: String? = null

    @SerializedName("wikipedia_url")
    var urlWiki: String? = null

    @SerializedName("vetstreet_url")
    var urlVetstreet: String? = null

    @SerializedName("image")
    var image: BreedsImageDTO? = null


}