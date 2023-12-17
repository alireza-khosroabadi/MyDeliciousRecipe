package com.delicious.homeUI.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeUI.screen.previewParams.RandomRecipeProvider
import com.delicious.homeUI.viewModel.HomeUiState
import com.delicious.homeUI.viewModel.HomeViewModel
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.ui.preview.ThemePreviews
import kotlin.math.absoluteValue

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
        contentPadding = PaddingValues(16.dp)
    ) { page ->
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                },
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            CardItem(recipe = data[page])
        }
    }
}


@Composable
fun CardItem(modifier: Modifier = Modifier, recipe: RandomRecipe) {
    val gradiantBackground = Brush.verticalGradient(listOf(Color(0xAA111111), Color(0x44111111)))
    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.image)
                .crossfade(true)
                .build(),
            contentDescription = recipe.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(brush = gradiantBackground)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(8.dp),
                text = recipe.title,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }

}

@ThemePreviews
@Composable
fun PreviewCardItem(@PreviewParameter(RandomRecipeProvider::class) item:List<RandomRecipe>) {
    RecipesTheme {
        RandomRecipeList(data = item)
    }
}

