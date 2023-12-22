@file:OptIn(ExperimentalMaterial3Api::class)
package com.delicious.systemdesign.component


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.delicious.ui.R
import com.delicious.ui.preview.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int?,
    navigationIcon: ImageVector = Icons.Default.Menu,
    navigationIconContentDescription: String?,
    actionIcon: Int? = null,
    actionIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes?: R.string.home)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon, //ImageVector.vectorResource(id = navigationIcon),
                    contentDescription = navigationIconContentDescription,
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = actionIcon),
                        contentDescription = actionIconContentDescription,
                        tint = Color.Unspecified
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier.testTag("hcTopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun RecipesTopAppBarPreview() {
    RecipesTopAppBar(
        titleRes = R.string.home,
        navigationIcon = Icons.Default.Menu,
        navigationIconContentDescription = "Navigation icon",
        actionIcon = null,
        actionIconContentDescription = "Action icon",
    )
}
