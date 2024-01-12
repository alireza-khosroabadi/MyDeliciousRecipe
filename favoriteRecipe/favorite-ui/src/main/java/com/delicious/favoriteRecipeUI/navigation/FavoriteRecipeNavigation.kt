package com.delicious.favoriteRecipeUI.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.delicious.favoriteRecipeUI.screen.FavoriteRecipeScreen

const val favoriteRecipeRoute = "favoriteRecipe_route"

fun NavController.navigateToFavoriteRecipe(navOptions: NavOptions? = null) {
    this.navigate(favoriteRecipeRoute, navOptions)
}

fun NavGraphBuilder.favoriteRecipeScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    navigateToRecipe: (recipeId: Int) -> Unit,
) {
    composable(favoriteRecipeRoute) {
        FavoriteRecipeScreen(navigateToRecipe = navigateToRecipe)
    }
}
