package com.marassu.petition

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.theme.PetitionTheme

class MainActivity : BaseActivity() {

    @Composable
    override fun Content() {
        PetitionTheme() {
            Greeting(name = "Android")
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }
}