package com.desmond.rightmove.domain.model

import com.desmond.rightmove.core.mappers.DomainModel
import com.squareup.moshi.Json

data class RecipeDomainModel(
    val recipeId: Int,
    val name: String,
    val cookTimeInMinutes: Int,
    val description: String,
    val instructions: List<InstructionsDomainModel>,
    val nutrition: NutritionDomainModel,
    val prepTimeInMinutes: Int,
    val numServings: Int,
    val servingsNounPlural: String,
    val servingsNounSingular: String,
    val thumbnailUrl: String,
    val negativeRating: Int,
    val positiveRating: Int,
) : DomainModel

data class InstructionsDomainModel(
    val instructionText: String,
    val position: Int,
) : DomainModel

data class NutritionDomainModel(
    val calories: Int,
    val carbohydrates: Int,
    val fat: Int,
    val fiber: Int,
    val protein: Int,
    val sugar: Int,
) : DomainModel