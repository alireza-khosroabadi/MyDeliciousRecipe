package com.delicious.recipes.ui.bottomBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.delicious.systemdesign.theme.AccentColor


@Composable
internal fun RecipesBottomBar(
    modifier: Modifier = Modifier,
    destinations: List<BottomNavBarDestination>,
    destinationsWithUnreadResources: Set<BottomNavBarDestination> = emptySet(),
    onNavigateToDestination: (BottomNavBarDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    RecipesNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val hasUnread = destinationsWithUnreadResources.contains(destination)
            val selected = currentDestination.isSelectedDestinationInHierarchy(destination)
            RecipesNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(destination.unselectedIcon),
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.selectedIcon),
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(id = destination.iconTextId)) },
                modifier = if (hasUnread) Modifier.notificationDot() else Modifier,
            )
        }
    }
}



@Composable
fun RecipesNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = RecipesNavigationDefaults.navigationContentColor(),
        tonalElevation = 8.dp,
        content = content,
        containerColor = MaterialTheme.colorScheme.surface,
    )
}


@Composable
fun RowScope.RecipesNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = RecipesNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = RecipesNavigationDefaults.navigationUnselectedContentColor(),
            selectedTextColor = RecipesNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = RecipesNavigationDefaults.navigationUnselectedContentColor(),
            indicatorColor = RecipesNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

private fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiaryColor = MaterialTheme.colorScheme.tertiary
        drawWithContent {
            drawContent()
            drawCircle(
                tertiaryColor,
                radius = 5.dp.toPx(),
                // This is based on the dimensions of the NavigationBar's "indicator pill";
                // however, its parameters are private, so we must depend on them implicitly
                // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
                center = center + Offset(
                    64.dp.toPx() * .45f,
                    32.dp.toPx() * -.45f - 6.dp.toPx(),
                ),
            )
        }
    }

private fun NavDestination?.isSelectedDestinationInHierarchy(destination: BottomNavBarDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


object RecipesNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationUnselectedContentColor() = Color.White

    @Composable
    fun navigationSelectedItemColor() = Color.Unspecified

    @Composable
    fun navigationSelectedTextColor() = AccentColor

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.surface
}
