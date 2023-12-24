@file:OptIn(ExperimentalLayoutApi::class)

package com.delicious.homeUI.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeUI.screen.previewParams.MealTypeProvider
import com.delicious.homeUI.viewModel.MealTypeUiState
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.ui.preview.ThemePreviews
import com.delicious.ui.shimmerLoadingAnimation


@Composable
fun MealTypeTableScreen(mealTypeUiState: MealTypeUiState) {
    when (mealTypeUiState) {
        MealTypeUiState.Loading -> Loading(modifier = Modifier.size(85.dp))
        is MealTypeUiState.Error -> Text(mealTypeUiState.message)
        is MealTypeUiState.MealTypes -> MealTypeTable(mealTypes = mealTypeUiState.mealTypes)
    }
}


@Composable
fun MealTypeTable(mealTypes: List<MealType>) {

    FlowRow(modifier = Modifier.fillMaxWidth(1f),
        maxItemsInEachRow = 3) {
        mealTypes.forEach {
            MealTypeItem(mealType = it)
        }
    }
}

@Composable
fun MealTypeItem(mealType: MealType) {
    val modifier = Modifier
        .padding(8.dp)
        .shadow(
            elevation = 8.dp,
            spotColor = Color(0x80000000),
            ambientColor = Color(0x80000000)
        )
        .size(85.dp)
        .background(
            color = Color.White,
            shape = MaterialTheme.shapes.small
        )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = mealType.icon),
            contentDescription = null,
            contentScale = ContentScale.None
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(id = mealType.title),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF999999)
            )
        )
    }

}

@Composable
fun Loading(modifier: Modifier) {
    Box(modifier = modifier.shimmerLoadingAnimation())
}

@ThemePreviews
@Composable
fun PreviewMealType(@PreviewParameter(MealTypeProvider::class) mealTypes: List<MealType>) {
    RecipesTheme {
        MealTypeTable(mealTypes = mealTypes)
    }
}

@ThemePreviews
@Composable
fun PreviewMealTypeItem(@PreviewParameter(MealTypeProvider::class) mealTypes: List<MealType>) {
    RecipesTheme {
        MealTypeItem(mealType = mealTypes[0])
    }
}