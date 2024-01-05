package com.delicious.homeUI.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.delicious.homeUI.screen.HomeScreen

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    navigateToRecipe: (recipeId:Int)-> Unit,
) {
    composable(route = homeRoute) {
        HomeScreen(navigateToRecipe= navigateToRecipe)
    }
}
