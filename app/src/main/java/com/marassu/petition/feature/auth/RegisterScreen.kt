package com.marassu.petition.feature.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marassu.petition.MainActivity
import com.marassu.petition.view.atom.BoxButton
import com.marassu.petition.view.atom.BoxTextField
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.atom.rememberBoxTextFieldState
import com.marassu.petition.view.theme.MainColor
import com.marassu.petition.view.theme.SubColor

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val emailBoxTextFieldState = rememberBoxTextFieldState(
        value = viewModel.email.collectAsState().value,
        hint = "이메일"
    )
    val passwordBoxTextFieldState = rememberBoxTextFieldState(
        value = viewModel.password.collectAsState().value,
        hint = "패스워드"
    )
    val nameBoxTextFieldState = rememberBoxTextFieldState(
        value = viewModel.name.collectAsState().value,
        hint = "이름"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LaunchedEffect(key1 = Unit) {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect{event ->
                    when(event) {
                        is RegisterViewModel.Event.Failure -> {
                            Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
        TopBar(title = "회원가입", isBack = true, onLeftClick = { navController.popBackStack() })
        Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
            Spacer(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth()
            )
            BoxTextField(
                onValueChange = { viewModel.setEmail(it) },
                state = emailBoxTextFieldState,
                isEmail = true
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
            )
            BoxTextField(
                onValueChange = { viewModel.setPassword(it) },
                state = passwordBoxTextFieldState,
                isPassword = true
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
            )
            BoxTextField(
                onValueChange = { viewModel.setName(it) },
                state = nameBoxTextFieldState
            )
            Spacer(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            )
            BoxButton(
                modifier = Modifier.background(SubColor),
                text = "회원가입",
                onClick = {
                    viewModel.register()
                })
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}