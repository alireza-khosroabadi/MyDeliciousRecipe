package com.delicious.recipeUI.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.delicious.recipeUI.screen.RecipeScreen
import java.net.URLDecoder
import java.net.URLEncoder

const val recipeRoute = "recipe_route"
private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

@VisibleForTesting
internal const val recipeIdArg = "recipeId"

internal class RecipeArgs(val recipeId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[recipeIdArg]),
                    URL_CHARACTER_ENCODING
                ).toInt()
            )
}

fun NavController.navigateToRecipe(recipeId: Int) {
    val encodedId = URLEncoder.encode(recipeId.toString(), URL_CHARACTER_ENCODING)
    this.navigate("$recipeRoute/$encodedId")
}

fun NavGraphBuilder.recipeScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = "$recipeRoute/{$recipeIdArg}", arguments = listOf(navArgument(recipeIdArg) {
        type = NavType.StringType
    })) {
        RecipeScreen()
    }
}
