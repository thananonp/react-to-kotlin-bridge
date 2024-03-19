package com.kotlinreactanotherbridge

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.kotlinreactanotherbridge.ui.OnboardCarousel
import com.kotlinreactanotherbridge.ui.ReferralNav
import com.kotlinreactanotherbridge.ui.WithdrawActivity
import com.kotlinreactanotherbridge.ui.enterTransition
import com.kotlinreactanotherbridge.ui.exitTransition
import com.kotlinreactanotherbridge.ui.popEnterTransition
import com.kotlinreactanotherbridge.ui.popExitTransition
import com.kotlinreactanotherbridge.ui.theme.CustomSnackBar
import com.kotlinreactanotherbridge.ui.theme.KotlinBaseTheme
import com.kotlinreactanotherbridge.ui.theme.WalletView
import com.kotlinreactanotherbridge.ui.theme.deposit
import com.kotlinreactanotherbridge.ui.theme.withdraw
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


  private lateinit var reactRootView: ReactRootView
  private lateinit var reactInstanceManager: ReactInstanceManager


  fun startReactNativeActivity() {
    val myIntent = Intent(this, WithdrawActivity::class.java)
//    myIntent.putExtra("key", value) //Optional parameters

    startActivity(myIntent)
  }

  @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val scope = rememberCoroutineScope()
      val snackbarHostState = remember { SnackbarHostState() }
      val navController = rememberNavController()

      KotlinBaseTheme {
        Box {
          // A surface container using the 'background' color from the theme
          Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
          ) {
            NavHost(
              navController = navController, startDestination = "onboard",
              enterTransition = { EnterTransition.None },
              exitTransition = { ExitTransition.None },
            ) {
              composable("onboard") {
                OnboardCarousel {
                  navController.navigate("home")
                }
              }
              composable("home") {
                Scaffold(bottomBar = {
                  BottomBarEiei(navController)
                }) {
                  ScreenHome(onClick = { navController.navigate("referral") }) {
                    scope.launch {
                      snackbarHostState.showSnackbar("Snack Bar TESTSTETSETSET")
                    }
                  }
                }
              }
              composable("wallet") {
                Scaffold(bottomBar = { BottomBarEiei(navController = navController) }) {
                  WalletView(onDeposit = {
                    navController.navigate("deposit")
                  }, onWithdraw = { startReactNativeActivity() })
                }
              }
              composable("more") {
                Scaffold(bottomBar = {
                  BottomBarEiei(navController)
                }) {
                  ScreenMore {
                    navController.navigate("referral")
                  }
                }
              }
//                referralGraph(navController)
              composable(
                "referral",
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
              ) {
                ReferralNav()
              }
              deposit(navController)
              withdraw(navController)
            }
          }

          SnackbarHost(
            modifier = Modifier
              .align(Alignment.TopCenter)
              .padding(top = 32.dp)
              .padding(horizontal = 16.dp), hostState = snackbarHostState
          ) { snackbarData: SnackbarData ->
            CustomSnackBar(
              androidx.core.R.drawable.ic_call_answer_low,
              snackbarData.visuals.message,
              isRtl = false,
              containerColor = Color.Green
            )
          }
        }
      }
    }


  }
}


sealed class Screen(val route: String) {
  object Profile : Screen("home")
  object Wallet : Screen("wallet")
  object More : Screen("more")
}

val items = listOf(
  Screen.Profile,
  Screen.Wallet,
  Screen.More,
)


@Composable
fun BottomBarEiei(
  navController: NavController
) {
  BottomNavigation {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    items.forEach { screen ->

      val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
      val label = screen.route + if (selected) " YES" else " NO"

      BottomNavigationItem(icon = {
        Icon(
          Icons.Filled.Favorite, contentDescription = null
        )
      }, label = { Text(text = label) }, selected = selected, onClick = {
        navController.navigate(screen.route) {
          // Pop up to the start destination of the graph to
          // avoid building up a large stack of destinations
          // on the back stack as users select items
          popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
          }
          // Avoid multiple copies of the same destination when
          // reselecting the same item
          launchSingleTop = true
          // Restore state when reselecting a previously selected item
          restoreState = true
        }
      })
    }
  }
}


@Composable
fun ScreenHome(onClick: () -> Unit, onMakeSnackBar: () -> Unit) {
  Column {
    Text(
      text = "Home"
    )
    Button(onClick = { onClick() }) {
      Text("Go referral screen")
    }
    Button(onClick = { onMakeSnackBar() }) {
      Text("Make SnackBar")
    }
  }
}

@Composable
fun ScreenMore(onClick: () -> Unit) {
  Column {
    Text("More")
    Button(onClick = { onClick() }) {
      Text("Go to referral")
    }
  }
}