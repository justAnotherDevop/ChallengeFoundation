package com.desmond.rightmove.data.model


import com.desmond.rightmove.core.mappers.DataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipesResponse(
    @Json(name = "recipes")
    val recipes: List<Recipe>
) : DataModel