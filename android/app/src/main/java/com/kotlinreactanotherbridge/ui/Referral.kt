package com.kotlinreactanotherbridge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun ReferralNav() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "introduction"){
    composable(
      "introduction",
      enterTransition = enterTransition,
      exitTransition = exitTransition,
      popEnterTransition = popEnterTransition,
      popExitTransition = popExitTransition
    ) {
      ScreenReferralIntroduction(onResult = { navController.navigate("result") },
        onTerms = { navController.navigate("terms") })
    }
    composable(
      "result",
      enterTransition = enterTransition,
      exitTransition = exitTransition,
      popEnterTransition = popEnterTransition,
      popExitTransition = popExitTransition
    ) {
      ScreenReferralResult()
    }
    composable(
      "terms",
      enterTransition = enterTransition,
      exitTransition = exitTransition,
      popEnterTransition = popEnterTransition,
      popExitTransition = popExitTransition
    ) {
      ScreenReferralTerms()
    }
  }
}

fun NavGraphBuilder.referralGraph(navController: NavController) {
  navigation(route = "referral", startDestination = "introduction") {
    composable(
      "introduction",
      enterTransition = enterTransition,
      exitTransition = exitTransition,
      popEnterTransition = popEnterTransition,
      popExitTransition = popExitTransition
    ) {
      ScreenReferralIntroduction(onResult = { navController.navigate("result") },
        onTerms = { navController.navigate("terms") })
    }
    composable(
      "result",
      enterTransition = enterTransition,
      exitTransition = exitTransition,
      popEnterTransition = popEnterTransition,
      popExitTransition = popExitTransition
    ) {
      ScreenReferralResult()
    }
    composable(
      "terms",
      enterTransition = enterTransition,
      exitTransition = exitTransition,
      popEnterTransition = popEnterTransition,
      popExitTransition = popExitTransition
    ) {
      ScreenReferralTerms()
    }
  }
}

@Composable
fun ScreenReferralIntroduction(
  onResult: () -> Unit, onTerms: () -> Unit
) {
  Column {
    Text("ชวนเพื่อนรับไปเลย 1 ล้านบาท!!!!!")
    Button(onClick = onResult) {
      Text("ดูผลชวนเพื่อน")
    }
    Button(onClick = onTerms) {
      Text("อ่านเงื่อนไข")
    }
  }
}

@Composable
fun ScreenReferralTerms() {
  Column {
    Text("TERMS")
    Text("TERMS")
    Text("TERMS")
    Text("TERMS")
    Text("TERMS")
  }
}

@Composable
fun ScreenReferralResult() {
  Column {
    Text("ชวนเพื่อนมาแล้ว")
    Text("100 คน")
    Text("ได้เงินมา")
    Text("1,000,000 บาท")
  }
}