package com.delicious.recipes

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.delicious.recipes.ui.RecipesApp
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.utility.network.NetworkMonitor
import com.google.accompanist.adaptive.calculateDisplayFeatures
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val TAG = "RecipesHomeActivity"

@AndroidEntryPoint
class RecipesHomeActivity : ComponentActivity() {


    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations, and go edge-to-edge
        // This also sets up the initial system bar style based on the platform theme
        enableEdgeToEdge()

        // add it before setContent
        installSplashScreen().setOnExitAnimationListener{screen ->
            val zoomX = ObjectAnimator.ofFloat(
                screen.iconView,
                View.SCALE_X,
                1f,
                0.0f
            ).apply {
            interpolator = OvershootInterpolator()
                duration = 500L
                doOnEnd { screen.remove() }
            }

            val zoomY = ObjectAnimator.ofFloat(
                screen.iconView,
                View.SCALE_Y,
                1f,
                0.0f
            ).apply {
                interpolator = OvershootInterpolator()
                duration = 500L
                doOnEnd { screen.remove() }
            }

            zoomX.start()
            zoomY.start()
        }

        setContent {

            val darkTheme = shouldUseDarkTheme()

            // Update the edge to edge configuration to match the theme
            // This is the same parameters as the default enableEdgeToEdge call, but we manually
            // resolve whether or not to show dark theme using uiState, since it can be different
            // than the configuration's dark theme value based on the user preference.
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

                RecipesTheme(
                    darkTheme = darkTheme,
                    androidTheme = shouldUseAndroidTheme(),
                    disableDynamicTheming = shouldDisableDynamicTheming(),
                ) {
                    RecipesApp(
                        networkMonitor = networkMonitor,
                        windowSizeClass = calculateWindowSizeClass(this),
                        displayFeatures = calculateDisplayFeatures(this)
                    )
                }
            }
    }
}

/**
 * Returns `true` if the dynamic color is disabled, as a function of the [uiState].
 */
private fun shouldDisableDynamicTheming(): Boolean = false
//@Composable
//private fun shouldDisableDynamicTheming(
//    uiState: MainActivityUiState,
//): Boolean = when (uiState) {
//    Loading -> false
//    is Success -> !uiState.userData.useDynamicColor
//}


/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
@Composable
fun shouldUseDarkTheme(): Boolean = isSystemInDarkTheme()
//@Composable
//private fun shouldUseDarkTheme(
//    uiState: MainActivityUiState,
//): Boolean = when (uiState) {
//    Loading -> isSystemInDarkTheme()
//    is Success -> when (uiState.userData.darkThemeConfig) {
//        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
//        DarkThemeConfig.LIGHT -> false
//        DarkThemeConfig.DARK -> true
//    }
//}

/**
 * Returns `true` if the Android theme should be used, as a function of the [uiState].
 */
@Composable
fun shouldUseAndroidTheme(): Boolean {
    return true
}
//@Composable
//private fun shouldUseAndroidTheme(
//    uiState: MainActivityUiState,
//): Boolean = when (uiState) {
//    Loading -> false
//    is Success -> when (uiState.userData.themeBrand) {
//        ThemeBrand.DEFAULT -> false
//        ThemeBrand.ANDROID -> true
//    }
//}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
