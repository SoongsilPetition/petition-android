package com.marassu.petition.feature.auth

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marassu.petition.MainActivity
import com.marassu.petition.R
import com.marassu.petition.view.atom.BoxButton
import com.marassu.petition.view.navigation.Screen
import com.marassu.petition.view.theme.MainColor
import com.marassu.petition.view.theme.SubColor
import com.marassu.petition.view.theme.notosanskr

@Composable
fun StartScreen(navController: NavController) {

    val viewModel: StartViewModel = hiltViewModel()
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    viewModel.checkLogin()
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is StartViewModel.Event.NavigateToHome -> {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier.size(260.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_full),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(180.dp))
        Box() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 24.dp, end = 24.dp)
            ) {
                BoxButton(
                    modifier = Modifier.background(MainColor), text = "로그인", onClick = {
                        navController.navigate(
                            Screen.Login.route
                        )
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                )
                BoxButton(
                    modifier = Modifier.background(SubColor), text = "회원가입", onClick = {
                        navController.navigate(
                            Screen.Register.route
                        )
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(rememberNavController())
}