package com.delicious.ui.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

private const val TIME_DURATION = 300

val enterTopTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInVertically(
        initialOffsetY = { -it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val exitBottomTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutVertically(
        targetOffsetY = { it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popEnterTopTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInVertically(
        initialOffsetY = { it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popExitBottomTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutVertically(
        targetOffsetY = { -it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val enterBottomTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInVertically(
        initialOffsetY = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val exitTopTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutVertically(
        targetOffsetY = { -it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popEnterBottomTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInVertically(
        initialOffsetY = { -it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popExitTopTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutVertically(
        targetOffsetY = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}