package com.marassu.petition.view.navigation

import androidx.annotation.DrawableRes
import com.marassu.petition.R

const val BOTTOM_GRAPH_ROUTE = "bottom"

sealed class Screen(val route: String, @DrawableRes val icon: Int? = null) {

    // Home
    object Home : Screen("home", R.drawable.home)
    object Category : Screen("category", R.drawable.list)
    object Completion : Screen("completion", R.drawable.comment_check)
    object Info : Screen("info", R.drawable.user)

}