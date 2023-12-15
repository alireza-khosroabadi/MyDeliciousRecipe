package com.delicious.homeUI.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeUI.viewModel.HomeUiState
import com.delicious.homeUI.viewModel.HomeViewModel
import okhttp3.internal.notifyAll

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
            .fillMaxWidth()
            .height(250.dp),
        state = pagerState,
    ) { page ->
        val item = data[page]
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .crossfade(true)
                .build(),
//            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxSize()
        )
    }
}