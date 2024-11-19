package com.desmond.rightmove.data.mappers

import com.desmond.rightmove.core.mappers.Mapper
import com.desmond.rightmove.data.model.Instruction
import com.desmond.rightmove.data.model.Nutrition
import com.desmond.rightmove.data.model.Recipe
import com.desmond.rightmove.domain.model.InstructionsDomainModel
import com.desmond.rightmove.domain.model.NutritionDomainModel
import com.desmond.rightmove.domain.model.RecipeDomainModel
import javax.inject.Inject

class RecipeToDomainMapper @Inject constructor(
    private val instructionsToDomainMapper: InstructionsToDomainMapper,
    private val nutritionToDomainMapper: NutritionToDomainMapper,
) :
    Mapper<Recipe, RecipeDomainModel> {
    override fun map(input: Recipe): RecipeDomainModel {
        return RecipeDomainModel(
            recipeId = input.id,
            name = input.name,
            cookTimeInMinutes = input.cookTimeMinutes,
            description = input.description,
            instructions = input.instructions
                .map { instruction ->
                    instructionsToDomainMapper.map(instruction)
                }.sortedBy { it.position },
            nutrition = nutritionToDomainMapper.map(input.nutrition),
            servingsNounSingular = input.servingsNounSingular,
            servingsNounPlural = input.servingsNounPlural,
            thumbnailUrl = input.thumbnailUrl,
            negativeRating = input.userRatings.countNegative,
            positiveRating = input.userRatings.countPositive,
            numServings = input.numServings,
            prepTimeInMinutes = input.prepTimeMinutes
        )
    }
}

class InstructionsToDomainMapper @Inject constructor() :
    Mapper<Instruction, InstructionsDomainModel> {
    override fun map(input: Instruction): InstructionsDomainModel {
        return InstructionsDomainModel(
            instructionText = input.displayText,
            position = input.position
        )
    }
}

class NutritionToDomainMapper @Inject constructor() : Mapper<Nutrition, NutritionDomainModel> {
    override fun map(input: Nutrition): NutritionDomainModel {
        return NutritionDomainModel(
            calories = input.calories,
            carbohydrates = input.carbohydrates,
            fat = input.fat,
            fiber = input.fiber,
            protein = input.protein,
            sugar = input.sugar,
        )
    }
}