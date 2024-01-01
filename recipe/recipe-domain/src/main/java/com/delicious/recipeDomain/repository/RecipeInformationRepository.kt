package com.delicious.recipeDomain.repository

import com.delicious.base.domain.ResultState
import com.delicious.recipeDomain.model.recipeInformation.RecipeInformation

interface RecipeInformationRepository {

    suspend fun recipeInformation(recipeId:Int): ResultState<RecipeInformation>
}
