package com.delicious.systemdesign.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.systemdesign.theme.LocalBackgroundTheme
import com.delicious.ui.preview.ThemePreviews

@Composable
fun RecipesBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val color = LocalBackgroundTheme.current.color
    val tonalElevation = LocalBackgroundTheme.current.tonalElevation
    Surface(
        color = if (color == Color.Unspecified) Color.Transparent else color,
        tonalElevation = if (tonalElevation == Dp.Unspecified) 0.dp else tonalElevation,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl,
            LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }
}

@ThemePreviews
@Composable
fun RecipesBackground(){
    RecipesTheme(disableDynamicTheming = true) {
        RecipesBackground(Modifier.size(100.dp), content = {})
    }
}
