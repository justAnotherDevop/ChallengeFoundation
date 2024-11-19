package com.desmond.rightmove.data.model


import com.desmond.rightmove.core.mappers.DataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Recipe(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cook_time_minutes")
    val cookTimeMinutes: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "instructions")
    val instructions: List<Instruction>,
    @Json(name = "name")
    val name: String,
    @Json(name = "num_servings")
    val numServings: Int,
    @Json(name = "nutrition")
    val nutrition: Nutrition,
    @Json(name = "prep_time_minutes")
    val prepTimeMinutes: Int,
    @Json(name = "servings_noun_plural")
    val servingsNounPlural: String,
    @Json(name = "servings_noun_singular")
    val servingsNounSingular: String,
    @Json(name = "thumbnail_url")
    val thumbnailUrl: String,
    @Json(name = "user_ratings")
    val userRatings: UserRatings,
) : DataModel