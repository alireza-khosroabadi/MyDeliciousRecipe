package com.delicious.favoriteDomain.useCase

import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
import javax.inject.Inject

class FavoriteRecipesUseCase
    @Inject
    constructor(private val favoriteRecipeRepository: FavoriteRecipeRepository) {
        operator fun invoke() = favoriteRecipeRepository.favoriteRecipes()
    }
