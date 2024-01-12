package com.delicious.favoriteRecipeUI.screen.previewParameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.delicious.favoriteDomain.model.FavoriteRecipe

class FavoriteRecipeProvider:PreviewParameterProvider<List<FavoriteRecipe>> {
    override val values: Sequence<List<FavoriteRecipe>>
        get() = sequenceOf(
            listOf(
                FavoriteRecipe(123, "title 1" , 10,"", 1,""),
                FavoriteRecipe(123, "title 2" , 20,"", 2,""),
                FavoriteRecipe(123, "title 3" , 30,"", 3,""),
            )
        )
}
