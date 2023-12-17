package com.delicious.homeUI.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeDomain.useCase.RandomRecipeUseCase
import com.delicious.ui.dispatcher.DispatcherModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val randomRecipeUseCase: RandomRecipeUseCase) :
    ViewModel() {


    val randomRecipeUiState = flow {
        emit(randomRecipeUseCase.invoke())
    }
        .map { result ->
            when (result) {
                is ResultState.Exception -> {
                    result.error.printStackTrace()
                    HomeUiState.ErrorMessage(result.error.message.orEmpty())
                }

                is ResultState.Failure -> HomeUiState.ErrorMessage(result.error)

                is ResultState.Success -> HomeUiState.randomRecipes(result.data)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading,
        )

}