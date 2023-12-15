package com.delicious.homeUI.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delicious.homeUI.viewModel.HomeUiState
import com.delicious.homeUI.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
val homeUiState by homeViewModel.randomRecipeUiState.collectAsStateWithLifecycle()
    Box (modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        ) {
        when(homeUiState){
            HomeUiState.Loading -> Text(text = "LOADING")
            is HomeUiState.ErrorMessage -> Text((homeUiState as HomeUiState.ErrorMessage).error)
            is HomeUiState.randomRecipes -> Text(text = (homeUiState as HomeUiState.randomRecipes).data.joinToString())
        }
    }
}