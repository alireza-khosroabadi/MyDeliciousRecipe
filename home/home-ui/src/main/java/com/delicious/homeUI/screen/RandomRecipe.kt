@file:OptIn(ExperimentalLayoutApi::class)

package com.delicious.homeUI.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.delicious.ui.R as uiR
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeUI.viewModel.RandomRecipeUiState

@Composable
fun RandomRecipe(randomRecipeUiState: RandomRecipeUiState) {
    when (randomRecipeUiState) {
        RandomRecipeUiState.Loading -> Loading(modifier = Modifier.size(85.dp))
        is RandomRecipeUiState.Error -> Text(randomRecipeUiState.message)
        is RandomRecipeUiState.RandomRecipes -> RandomRecipeList(recipes = randomRecipeUiState.randomRecipes)
    }
}

@Composable
fun RandomRecipeList(recipes: List<RandomRecipe>) {
    FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 1) {
        recipes.forEach {
            RandomRecipeItem(it)
        }
    }
}

@Composable
fun RandomRecipeItem(item: RandomRecipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        AsyncImage(
            model = item.image, contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.4f))
                .width(150.dp)
                .height(150.dp)
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = item.title,
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.size(8.dp))

            Row (verticalAlignment = Alignment.CenterVertically){
                Image(modifier = Modifier.size(24.dp),
                    painter = painterResource(id = uiR.drawable.ic_clock),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = item.readyInMinutes.toString(),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )
            }

        }
    }
}
