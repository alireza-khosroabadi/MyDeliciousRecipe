package com.delicious.homeDomain.useCase

import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject

class MealTypeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend operator fun invoke() = homeRepository.getMealType()
}