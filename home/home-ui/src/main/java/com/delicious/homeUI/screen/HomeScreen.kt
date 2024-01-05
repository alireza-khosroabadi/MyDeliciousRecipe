package com.delicious.homeUI.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delicious.feature.home.homeUI.R
import com.delicious.homeUI.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToRecipe: (recipeId: Int) -> Unit,
) {
    val popularRecipeUiState by homeViewModel.popularRecipeUiState.collectAsStateWithLifecycle()
    val mealTypeUiState by homeViewModel.mealTypeUiState.collectAsStateWithLifecycle()
    val randomRecipeUiState by homeViewModel.randomRecipeUiState.collectAsStateWithLifecycle()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.home_screen_promo_recipe),
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
        )
        PopularRecipeScreen(
            popularRecipeState = popularRecipeUiState,
            onRecipeClick = navigateToRecipe,
        )
        Spacer(modifier = Modifier.size(16.dp))
        MealTypeTableScreen(mealTypeUiState = mealTypeUiState)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Popular recipes",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
        )
        RandomRecipe(randomRecipeUiState = randomRecipeUiState, onFavoriteClick = {
            homeViewModel.favoriteRecipe(it)
        })
    }
}
