@file:OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalLayoutApi::class,
)

package com.delicious.homeUI.screen

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeUI.screen.previewParams.RandomRecipeProvider
import com.delicious.homeUI.viewModel.RandomRecipeUiState
import com.delicious.systemdesign.theme.RecipesTheme
import com.delicious.ui.R as uiR

@Composable
fun RandomRecipe(
    randomRecipeUiState: RandomRecipeUiState,
    onFavoriteClick: (recipe: RandomRecipe) -> Unit,
) {
    when (randomRecipeUiState) {
        RandomRecipeUiState.Loading -> Loading(modifier = Modifier.size(85.dp))
        is RandomRecipeUiState.Error -> Text(randomRecipeUiState.message)
        is RandomRecipeUiState.RandomRecipes ->
            RandomRecipeList(
                recipes = randomRecipeUiState.randomRecipes,
                onFavoriteClick,
            )
    }
}

@Composable
fun RandomRecipeList(
    recipes: List<RandomRecipe>,
    onFavoriteClick: (recipe: RandomRecipe) -> Unit,
) {
    FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 1) {
        recipes.forEach {
            RandomRecipeItem(it, onFavoriteClick)
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
fun RandomRecipeItem(
    item: RandomRecipe,
    onFavoriteClick: (recipe: RandomRecipe) -> Unit,
) {
    val context = LocalContext.current
    var isFavorite by remember {
        mutableStateOf(item.isFavorite)
    }
// Animates changes when `isFavorite` is changed.
    val transition = updateTransition(isFavorite, label = "selected state")
    val iconColor by transition.animateColor(label = "border color") { isFavorite ->
        if (isFavorite) Color.Unspecified else Color.LightGray
    }
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (image, title, durationIcon, duration, favorite, share, healthIcon, healthScore) = createRefs()
        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .clip(MaterialTheme.shapes.small)
                    .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.4f))
                    .width(120.dp)
                    .height(120.dp)
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
                        top.linkTo(parent.top)
                        start.linkTo(image.end)
                    },
        )

        Image(
            painter = painterResource(id = uiR.drawable.ic_clock),
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
            painter = painterResource(id = uiR.drawable.ic_health),
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
            onClick = { shareRecipe(item.sourceUrl, context) },
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
                    painter = painterResource(id = uiR.drawable.ic_share),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }

        IconToggleButton(
            modifier =
                Modifier.constrainAs(favorite) {
                    end.linkTo(share.start)
                    bottom.linkTo(parent.bottom)
                },
            checked = isFavorite,
            onCheckedChange =
                {
                    isFavorite = it
                    item.isFavorite = it
                    onFavoriteClick(item)
                },
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
                    painter = painterResource(id = uiR.drawable.ic_bookmark),
                    contentDescription = null,
                    tint = iconColor,
                )
            }
        }
    }
}

private fun shareRecipe(
    recipeUrl: String,
    context: Context,
) {
    val sendIntent =
        Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, recipeUrl)
            type = "text/plain"
        }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent, null)
}

@Preview(showBackground = true)
@Composable
fun PreviewRandomRecipe(
    @PreviewParameter(RandomRecipeProvider::class) recipe: List<RandomRecipe>,
) {
    RecipesTheme {
        Column {
            RandomRecipeItem(item = recipe[0]) {}
            RandomRecipeItem(item = recipe[1]) {}
        }
    }
}
