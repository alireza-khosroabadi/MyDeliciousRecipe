package com.delicious.homeUI.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delicious.feature.home.homeUI.R
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeUI.screen.previewParams.MealTypeProvider
import com.delicious.homeUI.viewModel.HomeViewModel
import com.delicious.homeUI.viewModel.MealTypeUiState
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.ui.preview.ThemePreviews

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val popularRecipeUiState by homeViewModel.randomRecipeUiState.collectAsStateWithLifecycle()
    val mealTypeUiState by homeViewModel.mealTypeUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.home_screen_promo_recipe),
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
        )
        PopularRecipeScreen(popularRecipeState = popularRecipeUiState)
        Spacer(modifier = Modifier.size(16.dp))
        MealTypeTableScreen(mealTypeUiState = mealTypeUiState)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Popular recipes",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black))

    }
}
