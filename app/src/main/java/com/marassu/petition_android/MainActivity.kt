package com.marassu.petition_android

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.marassu.petition_android.base.BaseActivity
import com.marassu.petition_android.view.theme.PetitionTheme

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