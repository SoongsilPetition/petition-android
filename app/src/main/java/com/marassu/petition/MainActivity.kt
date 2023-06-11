package com.marassu.petition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.atom.BottomBar
import com.marassu.petition.view.navigation.Screen
import com.marassu.petition.view.navigation.SetUpBottomNavGraph
import com.marassu.petition.view.theme.PetitionTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Composable
    override fun Content() {
        var currentRoute by remember { mutableStateOf(Screen.Home.route) }
        val navController = rememberNavController()

        PetitionTheme() {
            Scaffold(bottomBar = {
                BottomBar(selectedRoute = currentRoute, onItemSelected = {
                    currentRoute = it.route
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                })
            }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = it.calculateBottomPadding())
                ) {}
                SetUpBottomNavGraph(navController = navController)
            }
        }
    }
}