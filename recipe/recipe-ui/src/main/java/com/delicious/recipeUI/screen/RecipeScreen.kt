package com.delicious.recipeUI.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.delicious.recipeUI.viewModel.RecipeDetailViewModel

@Composable
fun RecipeScreen(viewModel: RecipeDetailViewModel = hiltViewModel()) {
    
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = viewModel.recipeId.toString())
    }
}
