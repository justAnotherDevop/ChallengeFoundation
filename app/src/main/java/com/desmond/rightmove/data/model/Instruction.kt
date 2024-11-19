package com.desmond.rightmove.data.model


import com.desmond.rightmove.core.mappers.DataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Instruction(
    @Json(name = "display_text")
    val displayText: String,
    @Json(name = "position")
    val position: Int,
) : DataModel