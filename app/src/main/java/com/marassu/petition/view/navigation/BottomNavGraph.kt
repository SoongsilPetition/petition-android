package com.marassu.petition.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marassu.petition.view.category.CategoryScreen
import com.marassu.petition.view.completion.CompletionScreen
import com.marassu.petition.view.home.HomeScreen
import com.marassu.petition.view.info.InfoScreen

@Composable
fun SetUpBottomNavGraph(
    navController: NavHostController,
    startRoute: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startRoute,
        route = BOTTOM_GRAPH_ROUTE
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Category.route) {
            CategoryScreen()
        }
        composable(route = Screen.Completion.route) {
            CompletionScreen()
        }
        composable(route = Screen.Info.route) {
            InfoScreen()
        }

    }
}