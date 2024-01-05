package com.delicious.homeUI.screen.previewParams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe

class PopularRecipeProvider : PreviewParameterProvider<List<PopularRecipe>> {
    override val values: Sequence<List<PopularRecipe>>
        get() = sequenceOf(
           listOf(
               PopularRecipe(123, "title 1" , "", ""),
               PopularRecipe(123, "title 2" , "", ""),
               PopularRecipe(123, "title 3" , "", ""),
           )
        )
}
