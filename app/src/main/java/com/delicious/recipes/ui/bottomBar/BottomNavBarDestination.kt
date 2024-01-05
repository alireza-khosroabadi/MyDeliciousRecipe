package com.delicious.recipes.ui.bottomBar

import com.delicious.recipes.R
import com.delicious.ui.R as uiR

enum class BottomNavBarDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = uiR.drawable.ic_home,
        unselectedIcon = uiR.drawable.ic_home,
        iconTextId = uiR.string.home,
        titleTextId = R.string.app_name,
    ),
    FAVORITE(
        selectedIcon = uiR.drawable.ic_favorite,
        unselectedIcon = uiR.drawable.ic_favorite,
        iconTextId = uiR.string.favorites,
        titleTextId = uiR.string.favorites,
    ),
}
