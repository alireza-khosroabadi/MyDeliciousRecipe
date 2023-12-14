package com.delicious.recipes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.delicious.recipes.R
import com.delicious.recipes.navigation.RecipesNavHost
import com.delicious.recipes.ui.bottomBar.RecipesBottomBar
import com.delicious.recipes.ui.state.RecipesAppState
import com.delicious.recipes.ui.state.rememberRecipesAppState
import com.delicious.systemdesign.component.RecipesBackground
import com.delicious.systemdesign.component.RecipesTopAppBar
import com.delicious.ui.utils.DisplayType
import com.delicious.ui.utils.FoldDeviceState
import com.delicious.ui.utils.isBookPosture
import com.delicious.ui.utils.isSeparating
import com.delicious.utility.network.NetworkMonitor

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun RecipesApp(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    networkMonitor: NetworkMonitor,
    appState: RecipesAppState = rememberRecipesAppState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass
    )
) {
    var onTopBarActionClick by rememberSaveable {
        mutableStateOf(false)
    }

    var showNavRail by remember {
        mutableStateOf(false)
    }

    var displayType by rememberSaveable {
        mutableStateOf(DisplayType.SINGLE_PANE)
    }

    val foldingDeviceState =
        foldDeviceState(displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull())

    displayType = displayType(windowSizeClass, foldingDeviceState)


    RecipesBackground {
        val snackbarHostState = remember { SnackbarHostState() }

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

        val notConnectedMessage = stringResource(R.string.not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite,
                )
            }
        }


        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar =
            {

                RecipesBottomBar(
                    destinations = appState.navBottomDestinations,
                    onNavigateToDestination = appState::navigateToBottomBarDestination,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier.testTag("RecipesBottomBar"),
                )
            },
        ) { paddingValues ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
//                if (appState.shouldShowNavRail) {
//                    RecipesNavRail(
//                        destinations = appState.navBottomDestinations,
//                        onNavigateToDestination = appState::navigateToBottomBarDestination,
//                        currentDestination = appState.currentDestination,
//                        modifier = Modifier
//                            .testTag("NiaNavRail")
//                            .safeDrawingPadding(),
//                    )
//                }

                Column(Modifier.fillMaxSize()) {
                    // Show the top app bar on top level destinations.
                    val destination = appState.currentNBottomBarDestination
                    if (destination != null) {
                        RecipesTopAppBar(
                            titleRes = destination.titleTextId,
                            navigationIconContentDescription = null,
                            actionIconContentDescription = null,
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent,
                            ),
                            onActionClick = { onTopBarActionClick = true },
                            onNavigationClick = { showNavRail = showNavRail.not() },
                        )
                    }

                    RecipesNavHost(appState = appState, onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short,
                        ) == SnackbarResult.ActionPerformed
                    })
                }
            }
        }

    }
}

@Composable
private fun foldDeviceState(foldingFeature: FoldingFeature?) = when {
    isBookPosture(foldingFeature) -> FoldDeviceState.BOOK

    isSeparating(foldingFeature) -> FoldDeviceState.SEPARATE

    else -> FoldDeviceState.NORMAL
}

@Composable
private fun displayType(
    windowSizeClass: WindowSizeClass,
    foldingDeviceState: FoldDeviceState
): DisplayType = when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> DisplayType.SINGLE_PANE

    WindowWidthSizeClass.Medium -> if (foldingDeviceState != FoldDeviceState.NORMAL) {
        DisplayType.DUAL_PANE
    } else {
        DisplayType.SINGLE_PANE
    }

    WindowWidthSizeClass.Expanded -> DisplayType.DUAL_PANE
    else -> DisplayType.SINGLE_PANE

}