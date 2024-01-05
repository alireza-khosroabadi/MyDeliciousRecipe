package com.delicious.homeDomain.useCase

import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject

class UpdateFavoriteRecipeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend operator fun invoke(recipe: RandomRecipe){
        homeRepository.updateFavoriteRecipe(recipe)
    }
}
