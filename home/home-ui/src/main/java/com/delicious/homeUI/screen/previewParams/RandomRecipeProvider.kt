package com.delicious.homeUI.screen.previewParams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

class RandomRecipeProvider : PreviewParameterProvider<List<RandomRecipe>> {
    override val values: Sequence<List<RandomRecipe>>
        get() = sequenceOf(
           listOf(
               RandomRecipe(123, "title 1" , "", ""),
               RandomRecipe(123, "title 2" , "", ""),
               RandomRecipe(123, "title 3" , "", ""),
           )
        )
}