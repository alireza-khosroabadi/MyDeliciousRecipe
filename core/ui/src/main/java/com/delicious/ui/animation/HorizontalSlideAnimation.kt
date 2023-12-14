package com.delicious.ui.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

private const val TIME_DURATION = 300

val enterLeftTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val exitRightTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popEnterLeftTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popExitRightTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val enterRightTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val exitLeftTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { -it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popEnterRightTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { -it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popExitLeftTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}