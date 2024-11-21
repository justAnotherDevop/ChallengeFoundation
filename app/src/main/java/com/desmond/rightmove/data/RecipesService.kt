package com.desmond.rightmove.data

import com.desmond.rightmove.data.model.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("list")
    suspend fun getRecipes(
        @Query("from") start: Int,
        @Query("size") size: Int,
    ): RecipesResponse
}