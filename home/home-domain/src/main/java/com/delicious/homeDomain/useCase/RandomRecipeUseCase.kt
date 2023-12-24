package com.delicious.homeDomain.useCase

import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject

class RandomRecipeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend operator fun invoke() = homeRepository.getRandomRecipe()
}