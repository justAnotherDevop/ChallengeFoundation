package com.desmond.rightmove.data.model


import android.provider.ContactsContract.Data
import com.desmond.rightmove.core.mappers.DataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRatings(
    @Json(name = "count_negative")
    val countNegative: Int,
    @Json(name = "count_positive")
    val countPositive: Int,
    @Json(name = "score")
    val score: Double
): DataModel