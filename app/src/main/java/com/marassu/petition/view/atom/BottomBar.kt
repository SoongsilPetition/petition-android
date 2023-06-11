package com.marassu.petition.view.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marassu.petition.view.navigation.Screen
import com.marassu.petition.view.theme.Disabled
import com.marassu.petition.view.theme.Selected
import com.marassu.petition.view.theme.Stroke

@Composable
fun BottomBar(
    selectedRoute: String,
    onItemSelected: (Screen) -> Unit
) {
    val screens = listOf(
        Screen.Home,
        Screen.Category,
        Screen.Completion,
        Screen.Info
    )
    Column() {
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Stroke)
        )
        Row(
            modifier = Modifier
                .height(44.dp)
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            screens.forEach {
                val isSelected = it.route == selectedRoute
                val color = if (isSelected) Selected else Disabled

                IconButton(
                    onClick = { if (!isSelected) onItemSelected(it) }) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = ImageVector.vectorResource(id = it.icon!!),
                        contentDescription = null,
                        tint = color
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomBottomBarNavigation() {
    val selectedRoute = Screen.Home.route
    BottomBar(selectedRoute = selectedRoute, onItemSelected = {})
}