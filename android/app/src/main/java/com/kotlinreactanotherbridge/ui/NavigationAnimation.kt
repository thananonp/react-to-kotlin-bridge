package com.kotlinreactanotherbridge.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

const val NAVIGATION_TIME_DURATION = 250
const val NAVIGATION_TIME_DURATION_LONG = NAVIGATION_TIME_DURATION.toLong()
val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
  slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(durationMillis = NAVIGATION_TIME_DURATION, easing = LinearEasing)
  )
}

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
  slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(durationMillis = NAVIGATION_TIME_DURATION, easing = LinearEasing)
  )
}

val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
  slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(durationMillis = NAVIGATION_TIME_DURATION, easing = LinearEasing)
  )
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
  slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(durationMillis = NAVIGATION_TIME_DURATION, easing = LinearEasing)
  )
}

val modalPopInTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
  {
    slideInVertically(
      initialOffsetY = { it },
      animationSpec = tween(durationMillis = NAVIGATION_TIME_DURATION, easing = LinearEasing)
    )
  }
val modalPopOutTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
  {
    slideOutVertically(
      targetOffsetY = { it },
      animationSpec = tween(durationMillis = NAVIGATION_TIME_DURATION, easing = LinearEasing)
    )
  }