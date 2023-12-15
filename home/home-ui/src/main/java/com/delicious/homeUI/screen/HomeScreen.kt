package com.delicious.homeUI.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeUI.viewModel.HomeUiState
import com.delicious.homeUI.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by homeViewModel.randomRecipeUiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (homeUiState) {
            HomeUiState.Loading -> Text(text = "LOADING")
            is HomeUiState.ErrorMessage -> Text((homeUiState as HomeUiState.ErrorMessage).error)
            is HomeUiState.randomRecipes -> RandomRecipeList(data = (homeUiState as HomeUiState.randomRecipes).data)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RandomRecipeList(modifier: Modifier = Modifier, data: List<RandomRecipe>) {
    val pagerState = rememberPagerState(pageCount = { data.size })
    HorizontalPager(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(200.dp),
        state = pagerState,
    ) { page ->
        // Our page content
        Text(
            text = data[page].title,
            modifier = Modifier.fillMaxWidth()
        )
    }
}