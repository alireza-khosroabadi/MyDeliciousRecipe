package com.delicious.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.delicious.ui.shimmerLoadingAnimation

@Composable
fun Loading(modifier: Modifier) {
    Box(modifier = modifier.shimmerLoadingAnimation(isLoadingCompleted = false))
}