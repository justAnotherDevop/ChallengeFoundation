package com.desmond.rightmove.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipesResponse(
    @Json(name = "results")
    val results: List<Recipe>
)