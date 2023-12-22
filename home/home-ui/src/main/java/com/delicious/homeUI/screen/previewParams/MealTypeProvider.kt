package com.delicious.homeUI.screen.previewParams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.delicious.feature.home.homeUI.R
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe

class MealTypeProvider : PreviewParameterProvider<List<MealType>> {
    override val values: Sequence<List<MealType>>
        get() = sequenceOf(
           listOf(
               MealType(title = com.delicious.ui.R.string.home , icon = com.delicious.ui.R.drawable.ic_cake),
               MealType(title = com.delicious.ui.R.string.home , icon = com.delicious.ui.R.drawable.ic_soup),
               MealType(title = com.delicious.ui.R.string.home , icon = com.delicious.ui.R.drawable.ic_carrots),
               MealType(title = com.delicious.ui.R.string.home , icon = com.delicious.ui.R.drawable.ic_maindish),
           )
        )
}