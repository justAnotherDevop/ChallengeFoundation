package com.desmond.rightmove.ui.model

import com.desmond.rightmove.core.mappers.PresentationModel

data class RecipeDetails(
    val recipeId: Int,
    val name: String,
    val cookTimeInMinutes: Int,
    val description: String,
    val instructions: List<RecipeInstruction>,
    val nutrition: RecipeNutrition,
    val prepTimeInMinutes: Int,
    val servingsText: String,
    val thumbnailUrl: String,
    val negativeRating: Int,
    val positiveRating: Int,
): PresentationModel

data class RecipeInstruction(
    val instructionText: String,
) : PresentationModel

data class RecipeNutrition(
    val calories: Int?,
    val carbohydrates: Int?,
    val fat: Int?,
    val fiber: Int?,
    val protein: Int?,
    val sugar: Int?,
) : PresentationModel