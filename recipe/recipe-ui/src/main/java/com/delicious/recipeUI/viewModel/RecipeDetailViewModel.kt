package com.delicious.recipeUI.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.delicious.recipeUI.navigation.RecipeArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(stateHandle: SavedStateHandle):ViewModel() {

    private val recipeArgs = RecipeArgs(stateHandle)
     val recipeId = recipeArgs.recipeId
}
