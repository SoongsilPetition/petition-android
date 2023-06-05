package com.marassu.petition.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.theme.PetitionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    @Preview
    @Composable
    override fun Content() {
        PetitionTheme {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                emailTextField()
                passwordTextField()
                loginButton()
            }
        }
    }

    @Composable
    fun emailTextField(viewModel: LoginViewModel = hiltViewModel()) {
        val email = viewModel.email.collectAsState()

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = email.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onValueChange = viewModel::setEmail,
            label = { Text("email") }
        )
    }

    @Composable
    fun passwordTextField(viewModel: LoginViewModel = hiltViewModel()) {
        val password = viewModel.password.collectAsState()

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = password.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            onValueChange = viewModel::setPassword,
            label = { Text("password") }
        )
    }

    @Composable
    fun loginButton(viewModel: LoginViewModel = hiltViewModel()) {
        Button(onClick = { viewModel.login() }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text("로그인")
        }
    }
}