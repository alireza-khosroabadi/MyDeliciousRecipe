package com.delicious.homeUI.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeDomain.useCase.MealTypeUseCase
import com.delicious.homeDomain.useCase.PopularRecipeUseCase
import com.delicious.homeDomain.useCase.RandomRecipeUseCase
import com.delicious.homeDomain.useCase.UpdateFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val popularRecipeUseCase: PopularRecipeUseCase,
        private val mealTypeUseCase: MealTypeUseCase,
        private val randomRecipeUseCase: RandomRecipeUseCase,
        private val updateFavoriteRecipeUseCase: UpdateFavoriteRecipeUseCase,
    ) :
    ViewModel() {
        val popularRecipeUiState =
            flow {
                emit(popularRecipeUseCase.invoke())
            }
                .map { result ->
                    when (result) {
                        is ResultState.Exception -> {
                            PopularRecipeUiState.Error(
                                errorCode = 0,
                                message = result.error.message.orEmpty(),
                            )
                        }

                        is ResultState.Failure ->
                            PopularRecipeUiState.Error(
                                errorCode = result.code,
                                message = result.error,
                            )

                        is ResultState.Success -> {
                            PopularRecipeUiState.PopularRecipes(result.data)
                        }
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = PopularRecipeUiState.Loading,
                )

        val mealTypeUiState =
            flow {
                emit(mealTypeUseCase.invoke())
            }
                .map { result ->
                    MealTypeUiState.MealTypes(mealTypes = (result as ResultState.Success).data)
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = MealTypeUiState.Loading,
                )

        val randomRecipeUiState =
            flow {
                emit(randomRecipeUseCase())
            }
                .map { result ->
                    when (result) {
                        is ResultState.Exception -> {
                            RandomRecipeUiState.Error(
                                errorCode = 0,
                                message = result.error.message.orEmpty(),
                            )
                        }

                        is ResultState.Failure ->
                            RandomRecipeUiState.Error(
                                errorCode = result.code,
                                message = result.error,
                            )

                        is ResultState.Success -> {
                            RandomRecipeUiState.RandomRecipes(result.data)
                        }
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = RandomRecipeUiState.Loading,
                )

        fun favoriteRecipe(recipe: RandomRecipe) {
            viewModelScope.launch {
                updateFavoriteRecipeUseCase.invoke(recipe)
            }
        }
    }
