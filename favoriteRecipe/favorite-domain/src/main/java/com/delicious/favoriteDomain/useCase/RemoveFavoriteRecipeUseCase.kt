package com.delicious.favoriteDomain.useCase

import com.delicious.favoriteDomain.model.FavoriteRecipe
import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
import javax.inject.Inject

class RemoveFavoriteRecipeUseCase
    @Inject
    constructor(private val favoriteRecipeRepository: FavoriteRecipeRepository) {
        suspend operator fun invoke(favoriteRecipe: FavoriteRecipe)  {
            favoriteRecipeRepository.deleteRecipe(favoriteRecipe)
        }
    }
