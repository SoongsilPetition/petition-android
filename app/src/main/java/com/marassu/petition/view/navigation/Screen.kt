package com.marassu.petition.view.navigation

import androidx.annotation.DrawableRes
import com.marassu.petition.R

const val AUTH_GRAPH_ROUTE = "auth"
const val BOTTOM_GRAPH_ROUTE = "bottom"

sealed class Screen(val route: String, @DrawableRes val icon: Int? = null) {

    // Auth
    object Start : Screen("start")
    object Login : Screen("login")
    object Register : Screen("register")

    // Home
    object Home : Screen("home", R.drawable.home)
    object Category : Screen("category", R.drawable.list)
    object Completion : Screen("completion", R.drawable.comment_check)
    object Info : Screen("info", R.drawable.user)

}