package com.desmond.rightmove.domain.mappers

import com.desmond.rightmove.core.mappers.Mapper
import com.desmond.rightmove.data.mappers.NutritionToDomainMapper
import com.desmond.rightmove.domain.model.InstructionsDomainModel
import com.desmond.rightmove.domain.model.NutritionDomainModel
import com.desmond.rightmove.domain.model.RecipeDomainModel
import com.desmond.rightmove.ui.model.RecipeDetails
import com.desmond.rightmove.ui.model.RecipeInstruction
import com.desmond.rightmove.ui.model.RecipeNutrition
import javax.inject.Inject

class RecipeDomainToPresentationMapper @Inject constructor(
    private val instructionToPresentationMapper: InstructionDomainToPresentationMapper,
    private val nutritionToPresentationMapper: NutritionDomainToPresentationMapper,
) : Mapper<RecipeDomainModel, RecipeDetails> {
    override fun map(input: RecipeDomainModel): RecipeDetails {
        return RecipeDetails(
            recipeId = input.recipeId,
            name = input.name,
            cookTimeInMinutes = input.cookTimeInMinutes,
            description = input.description,
            instructions = input.instructions.map { instruction ->
                instructionToPresentationMapper.map(instruction)
            },
            prepTimeInMinutes = input.prepTimeInMinutes,
            servingsText = getServingsText(
                servingsCount = input.numServings,
                servingsTextSingular = input.servingsNounSingular,
                servingsTextPlural = input.servingsNounPlural
            ),
            thumbnailUrl = input.thumbnailUrl,
            negativeRating = input.negativeRating,
            positiveRating = input.positiveRating,
            nutrition = nutritionToPresentationMapper.map(input.nutrition)
        )
    }

    private fun getServingsText(
        servingsCount: Int,
        servingsTextSingular: String,
        servingsTextPlural: String
    ): String {
        return if (servingsCount > 1) {
            "$servingsCount $servingsTextPlural"
        } else {
            "$servingsCount $servingsTextSingular"
        }
    }
}

class InstructionDomainToPresentationMapper @Inject constructor(

) : Mapper<InstructionsDomainModel, RecipeInstruction> {
    override fun map(input: InstructionsDomainModel): RecipeInstruction {
        return RecipeInstruction(
            instructionText = input.instructionText,
        )
    }

}

class NutritionDomainToPresentationMapper @Inject constructor(

) : Mapper<NutritionDomainModel, RecipeNutrition> {
    override fun map(input: NutritionDomainModel): RecipeNutrition {
        return RecipeNutrition(
            calories = input.calories,
            carbohydrates = input.carbohydrates,
            fat = input.fat,
            fiber = input.fiber,
            protein = input.protein,
            sugar = input.sugar,
        )
    }

}