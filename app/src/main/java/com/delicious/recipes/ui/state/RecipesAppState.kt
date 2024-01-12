package com.delicious.recipes.ui.state

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.delicious.favoriteRecipeUI.navigation.favoriteRecipeRoute
import com.delicious.favoriteRecipeUI.navigation.navigateToFavoriteRecipe
import com.delicious.homeUI.navigation.homeRoute
import com.delicious.homeUI.navigation.navigateToHome
import com.delicious.recipes.ui.bottomBar.BottomNavBarDestination
import com.delicious.utility.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberRecipesAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): RecipesAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor
    ) {
        RecipesAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor
        )
    }
}

@Stable
class RecipesAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentNBottomBarDestination: BottomNavBarDestination?
        @Composable get() = when (currentDestination?.route) {
            homeRoute -> BottomNavBarDestination.HOME
            favoriteRecipeRoute -> BottomNavBarDestination.FAVORITE
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val navBottomDestinations: List<BottomNavBarDestination> = BottomNavBarDestination.entries


    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param bottomNavBarDestination: The destination the app needs to navigate to.
     */
    fun navigateToBottomBarDestination(bottomNavBarDestination: BottomNavBarDestination) {
            val bottomBarNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (bottomNavBarDestination) {
                BottomNavBarDestination.HOME -> navController.navigateToHome(bottomBarNavOptions)
                BottomNavBarDestination.FAVORITE -> navController.navigateToFavoriteRecipe(bottomBarNavOptions)
        }

    }
}
