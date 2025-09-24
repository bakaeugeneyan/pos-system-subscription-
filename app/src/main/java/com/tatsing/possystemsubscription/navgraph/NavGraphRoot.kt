package com.tatsing.possystemsubscription.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tatsing.possystemsubscription.AppAuth
import com.tatsing.possystemsubscription.ui.main.MainScreen
import com.tatsing.possystemsubscription.ui.signin.SignInScreen

sealed class RootScreen(val route: String) {
    data object SignIn : RootScreen(route = "signin_screen")
    data object Main : RootScreen(route = "main_screen")
}

@Composable
fun NavGraphRoot(
    navController: NavHostController,
) {
	val isUserLoggedIn by AppAuth.isLoggedIn.collectAsState()

    val startDestination = when {
        !isUserLoggedIn -> RootScreen.SignIn.route
        else -> RootScreen.Main.route
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(navController = navController, startDestination = startDestination) {
			composable(route = RootScreen.SignIn.route) {
				SignInScreen(navController = navController, paddingValues = innerPadding)
			}
			composable(route = RootScreen.Main.route) {
				MainScreen()
			}
        }
    }
}

fun NavHostController.popToStartNavigateSingleTopTo(route: String) = this.navigate(route) {

    // Pop up to the start destination of the graph to avoid building up a large stack of destinations on the back stack as users select items
    popUpTo(this@popToStartNavigateSingleTopTo.graph.findStartDestination().id) {
        saveState = true
    }

    // Avoid multiple copies of the same destination when reselecting the same item
    launchSingleTop = true
    // Restore state when reselecting a previously selected item
    restoreState = true
}