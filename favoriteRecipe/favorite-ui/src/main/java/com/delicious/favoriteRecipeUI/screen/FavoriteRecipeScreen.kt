package com.delicious.favoriteRecipeUI.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.delicious.favoriteDomain.model.FavoriteRecipe
import com.delicious.favoriteRecipeUI.screen.previewParameter.FavoriteRecipeProvider
import com.delicious.favoriteRecipeUI.viewModel.FavoriteRecipeViewModel
import com.delicious.favoriteRecipeUI.viewModel.FavoriteUiState
import com.delicious.systemdesign.theme.AccentColor
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.ui.R
import com.delicious.ui.shimmerLoadingAnimation
import com.delicious.utility.extensions.share.shareString

@Composable
fun FavoriteRecipeScreen(
    viewModel: FavoriteRecipeViewModel = hiltViewModel(),
    navigateToRecipe: (recipeId: Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoriteRecipe(uiState) {
        viewModel.removeFavoriteRecipe(it)
    }
}

@Composable
fun FavoriteRecipe(
    favoriteRecipeUiState: FavoriteUiState,
    removeFavoriteClick: (recipe: FavoriteRecipe) -> Unit,
) {
    when (favoriteRecipeUiState) {
        FavoriteUiState.Loading -> Loading(modifier = Modifier.fillMaxWidth())
        is FavoriteUiState.Error -> Text(favoriteRecipeUiState.exception.message.toString())
        is FavoriteUiState.FavoriteRecipes ->
            FavoriteRecipeList(
                recipes = favoriteRecipeUiState.data,
                removeFavoriteClick,
            )
    }
}

@Composable
fun FavoriteRecipeList(
    recipes: List<FavoriteRecipe>,
    removeFavoriteClick: (recipe: FavoriteRecipe) -> Unit,
) {
    LazyColumn(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        items(items = recipes, key = { it.id }) {
            FavoriteRecipeItem(it, removeFavoriteClick)
        }
    }
}

@Composable
fun FavoriteRecipeItem(
    item: FavoriteRecipe,
    removeFavoriteClick: (recipe: FavoriteRecipe) -> Unit,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.White),
    ) {
        ConstraintLayout(
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            val (image, title, durationIcon, duration, delete, share, healthIcon, healthScore) = createRefs()
            AsyncImage(
                model = item.image,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier =
                Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(120.dp)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                    },
            )

            Text(
                text = item.title,
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    .constrainAs(title) {
                        top.linkTo(delete.top)
                        start.linkTo(image.end)
                        end.linkTo(delete.start)
                        width = Dimension.fillToConstraints
                    },
            )

            IconButton(
                modifier =
                    Modifier.constrainAs(delete) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                onClick = { removeFavoriteClick(item) },
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = AccentColor,
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = null,
                modifier =
                    Modifier
                        .padding(top = 16.dp, start = 8.dp)
                        .size(20.dp)
                        .constrainAs(durationIcon) {
                            top.linkTo(title.bottom)
                            start.linkTo(title.start)
                        },
            )

            Text(
                text = item.readyInMinutes.toString(),
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                modifier =
                    Modifier
                        .padding(top = 12.dp, start = 4.dp)
                        .constrainAs(duration) {
                            bottom.linkTo(durationIcon.bottom)
                            top.linkTo(durationIcon.top)
                            start.linkTo(durationIcon.end)
                        },
            )

            Image(
                painter = painterResource(id = R.drawable.ic_health),
                contentDescription = null,
                modifier =
                    Modifier
                        .padding(top = 16.dp, start = 8.dp)
                        .size(24.dp)
                        .constrainAs(healthIcon) {
                            top.linkTo(durationIcon.top)
                            start.linkTo(title.start)
                            end.linkTo(parent.end)
                        },
            )

            Text(
                text = item.healthScore.toString(),
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                modifier =
                    Modifier
                        .padding(top = 12.dp, start = 4.dp)
                        .constrainAs(healthScore) {
                            bottom.linkTo(healthIcon.bottom)
                            top.linkTo(healthIcon.top)
                            start.linkTo(healthIcon.end)
                        },
            )

            IconButton(
                modifier =
                    Modifier.constrainAs(share) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                onClick = { context.shareString(item.sourceUrl) },
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(30.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(Color(0XFFFFF6EB)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }
}

@Composable
fun Loading(modifier: Modifier) {
    LazyColumn() {
        items(5){
            Box(modifier = modifier.height(120.dp).shimmerLoadingAnimation())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteRecipe(
    @PreviewParameter(FavoriteRecipeProvider::class) recipe: List<FavoriteRecipe>,
) {
    RecipesTheme {
            FavoriteRecipeItem(item = recipe[0]) {}
    }
}
