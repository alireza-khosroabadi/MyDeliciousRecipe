package com.delicious.recipes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.delicious.homeUI.navigation.homeRoute
import com.delicious.homeUI.navigation.homeScreen
import com.delicious.recipes.ui.state.RecipesAppState


@Composable
fun RecipesNavHost(
    appState: RecipesAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onShowSnackbar)
    }
}