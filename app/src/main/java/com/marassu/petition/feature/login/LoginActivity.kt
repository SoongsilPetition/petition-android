package com.marassu.petition.feature.login

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.theme.PetitionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    @Composable
    override fun Content() {
        PetitionTheme {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val viewModel: LoginViewModel = hiltViewModel()
                EmailTextField(viewModel::setEmail)
                PasswordTextField(viewModel::setPassword)
                LoginButton(viewModel::login)
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun PreviewLoginActivity() {
        PetitionTheme {
            Column {
                EmailTextField(onValueChange = { })
                PasswordTextField(onValueChange = { })
                LoginButton { }
            }
        }
    }


    @Composable
    fun EmailTextField(onValueChange: (String) -> Unit) {
        val email = remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = email.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                email.value = it
                onValueChange(it)
            },
            label = { Text("email") }
        )
    }

    @Composable
    fun PasswordTextField(onValueChange: (String) -> Unit) {
        val password = remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = password.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password.value = it
                onValueChange(it)
            },
            label = { Text("password") }
        )
    }

    @Composable
    fun LoginButton(onClick: () -> Unit) {
        Button(
            onClick = onClick, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("로그인")
        }
    }
}