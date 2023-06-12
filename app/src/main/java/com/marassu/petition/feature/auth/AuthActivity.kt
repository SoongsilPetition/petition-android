package com.marassu.petition.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.navigation.SetUpAuthGraph
import com.marassu.petition.view.theme.PetitionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    @Composable
    override fun Content() {
        PetitionTheme {
            val navController = rememberNavController()
            SetUpAuthGraph(navController = navController)
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun PreviewLoginActivity() {
        PetitionTheme {
            PetitionTheme {
                val navController = rememberNavController()
                SetUpAuthGraph(navController = navController)
            }
        }
    }
}