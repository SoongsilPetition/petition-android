package com.marassu.petition.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marassu.petition.feature.auth.StartScreen
import com.marassu.petition.feature.auth.LoginScreen
import com.marassu.petition.feature.auth.RegisterScreen

@Composable
fun SetUpAuthGraph(
    navController: NavHostController,
    startRoute: String = Screen.Start.route
) {
    NavHost(
        navController = navController,
        startDestination = startRoute,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(route = Screen.Start.route) {
            StartScreen(navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
    }
}