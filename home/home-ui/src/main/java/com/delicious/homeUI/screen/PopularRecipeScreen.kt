package com.delicious.homeUI.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeUI.screen.previewParams.RandomRecipeProvider
import com.delicious.homeUI.viewModel.PopularRecipeUiState
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.ui.preview.ThemePreviews
import kotlin.math.absoluteValue


@Composable
fun PopularRecipeScreen(
    popularRecipeState: PopularRecipeUiState,
    onRecipeClick: (recipeId: Int) -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)

    when (popularRecipeState) {
        PopularRecipeUiState.Loading -> {
            Loading(modifier = modifier.clip(MaterialTheme.shapes.small))
        }
        is PopularRecipeUiState.Error -> {
            Text(popularRecipeState.message)
        }
        is PopularRecipeUiState.PopularRecipes -> {
            PopularRecipeList(data = popularRecipeState.data , modifier = modifier, onRecipeClick=onRecipeClick)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularRecipeList(modifier: Modifier = Modifier, data: List<PopularRecipe>, onRecipeClick: (recipeId: Int) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { data.size })
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
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
            CardItem(recipe = data[page], onRecipeClick = onRecipeClick)
        }
    }
}


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    recipe: PopularRecipe,
    onRecipeClick: (recipeId: Int) -> Unit
) {
    val gradiantBackground = Brush.verticalGradient(listOf(Color(0xAA111111), Color(0x44111111)))
    Box(modifier = modifier.fillMaxSize().clickable { onRecipeClick(recipe.id) }) {
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
fun PreviewCardItem(@PreviewParameter(RandomRecipeProvider::class) item:List<PopularRecipe>) {
    RecipesTheme {
        PopularRecipeList(data = item){}
    }
}
