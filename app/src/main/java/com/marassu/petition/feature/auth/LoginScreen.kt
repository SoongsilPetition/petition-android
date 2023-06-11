package com.marassu.petition.feature.auth

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marassu.petition.MainActivity
import com.marassu.petition.view.atom.BoxButton
import com.marassu.petition.view.atom.BoxTextField
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.atom.rememberBoxTextFieldState
import com.marassu.petition.view.theme.MainColor

@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current
    val emailBoxTextFieldState = rememberBoxTextFieldState(
        value = viewModel.email.collectAsState().value,
        hint = "이메일"
    )
    val passwordBoxTextFieldState = rememberBoxTextFieldState(
        value = viewModel.password.collectAsState().value,
        hint = "패스워드"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(title = "로그인", isBack = true, onLeftClick = { navController.popBackStack() })
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
                    .height(40.dp)
                    .fillMaxWidth()
            )
            BoxButton(
                modifier = Modifier.background(MainColor),
                text = "로그인",
                onClick = {
                    viewModel.login()
                    val homeIntent = Intent(context, MainActivity::class.java)
                    Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    context.startActivity(homeIntent)
                    (context as Activity).finish()
                })
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}