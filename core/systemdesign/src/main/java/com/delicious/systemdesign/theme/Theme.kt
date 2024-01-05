package com.delicious.systemdesign.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

///**
// * Light default theme color scheme
// */
//@VisibleForTesting
//val LightDefaultColorScheme = lightColorScheme(
//    primary = PrimaryColor,
//    secondary = DarkPrimaryColor,
//    surface = SecondaryBackgroundColor,
//    primaryContainer = SecondaryBackgroundColor,
//    onPrimaryContainer = AccentColor,
//    onSurfaceVariant = SecondaryBackgroundColor
//)
//
///**
// * Dark default theme color scheme
// */
//@VisibleForTesting
//val DarkDefaultColorScheme = darkColorScheme(
//    primary = PrimaryColor,
//    secondary = DarkPrimaryColor,
//    surface = SecondaryBackgroundColor,
//    primaryContainer = SecondaryBackgroundColor,
//    onPrimaryContainer = AccentColor,
//    onSurfaceVariant = SecondaryBackgroundColor
//)


/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = PrimaryBackgroundColor)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = PrimaryBackgroundColor)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */


@Composable
fun RecipesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {

    // Color scheme
    val colorScheme = when {
        androidTheme -> if (darkTheme) LightDefaultColorScheme else DarkDefaultColorScheme
//        !disableDynamicTheming && supportsDynamicTheming() -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }


    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }

    // Composition locals
    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = RecipesTypography,
            content = content,
        )
    }
}



@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
