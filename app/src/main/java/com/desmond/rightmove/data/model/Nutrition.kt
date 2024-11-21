package com.desmond.rightmove.data.model


import com.desmond.rightmove.core.mappers.DataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Nutrition(
    @Json(name = "calories")
    val calories: Int?,
    @Json(name = "carbohydrates")
    val carbohydrates: Int?,
    @Json(name = "fat")
    val fat: Int?,
    @Json(name = "fiber")
    val fiber: Int?,
    @Json(name = "protein")
    val protein: Int?,
    @Json(name = "sugar")
    val sugar: Int?,
): DataModel