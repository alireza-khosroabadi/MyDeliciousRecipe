package com.delicious.favoriteDomain.fakeData

import com.delicious.base.domain.ResultState
import com.delicious.favoriteDomain.model.FavoriteRecipe
import kotlinx.coroutines.flow.flow

val fakeFavoriteRecipeList =
    listOf(
        FavoriteRecipe(
            0,
            "",
            0,
            "",
            0,
            "",
        ),
        FavoriteRecipe(
            1,
            "",
            0,
            "",
            0,
            "",
        ),
    )

val fakeFavoriteRecipeListAsFlow =
    flow {
        emit(ResultState.Success(fakeFavoriteRecipeList))
    }
