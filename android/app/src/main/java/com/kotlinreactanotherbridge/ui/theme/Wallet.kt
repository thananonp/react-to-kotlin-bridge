package com.kotlinreactanotherbridge.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotlinreactanotherbridge.ui.modalPopInTransition
import com.kotlinreactanotherbridge.ui.modalPopOutTransition


fun NavGraphBuilder.deposit(navController: NavController) {
  navigation(route = "deposit", startDestination = "method") {
    composable(
      "method",
      enterTransition = modalPopInTransition,
      exitTransition = modalPopOutTransition,
      popEnterTransition = modalPopInTransition,
      popExitTransition = modalPopOutTransition
    ) {
      DepositView()
    }

  }
}

fun NavGraphBuilder.withdraw(navController: NavController) {
  navigation(route = "withdraw", startDestination = "method") {
    composable(
      "method",
      enterTransition = modalPopInTransition,
      exitTransition = modalPopOutTransition,
      popEnterTransition = modalPopInTransition,
      popExitTransition = modalPopOutTransition
    ) {
      WithdrawView()
    }
  }
}

@Composable
fun WalletView(
  onDeposit: () -> Unit,
  onWithdraw: () -> Unit,
) {
  Column {
    Button(onClick = onDeposit) {
      Text("Deposit")
    }

    Button(onClick = onWithdraw) {
      Text("Withdraw")
    }
  }
}

@Composable
fun DepositView() {
  Column {
    Text(text = "Deposit")
    Text(text = "Method 1")
    Text(text = "Method 2")
    Text(text = "Method 3")
  }
}

@Composable
fun WithdrawView() {
  Column {
    Text(text = "Withdraw")
    Text(text = "Method 1")
    Text(text = "Method 2")
    Text(text = "Method 3")
  }
}