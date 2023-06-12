package com.marassu.petition.feature.write

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.theme.PetitionTheme
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.TextSub
import com.marassu.petition.view.theme.notosanskr
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteActivity : BaseActivity() {
    @Composable
    override fun Content() {
        val viewModel: WriteViewModel = hiltViewModel()

        PetitionTheme {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                TopBar(
                    title = "청원 작성",
                    isBack = false,
                    onLeftClick = { finish() },
                    onRightClick = { viewModel.write() })
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
                Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.title.collectAsState().value,
                        onValueChange = { viewModel.setTitle(it) },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = notosanskr,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = TextMain,
                            backgroundColor = Color.Transparent,
                            cursorColor = TextMain,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = TextMain
                        ),
                        placeholder = {
                            Text(
                                text = "제목", color = Color.Gray, style = TextStyle(
                                    fontFamily = notosanskr,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                                )
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
                Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.category.collectAsState().value,
                        onValueChange = { viewModel.setCategory(it) },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = notosanskr,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = TextSub,
                            backgroundColor = Color.Transparent,
                            cursorColor = TextSub,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = TextSub
                        ),
                        placeholder = {
                            Text(
                                text = "카테고리", color = Color.Gray, style = TextStyle(
                                    fontFamily = notosanskr,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                                )
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
                Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.content.collectAsState().value,
                        onValueChange = { viewModel.setContent(it) },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = notosanskr,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = TextMain,
                            backgroundColor = Color.Transparent,
                            cursorColor = TextMain,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = TextMain
                        ),
                        placeholder = {
                            Text(
                                text = "글 내용 작성", color = Color.Gray, style = TextStyle(
                                    fontFamily = notosanskr,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                                )
                            )
                        }
                    )
                }
            }
        }
    }

    @Preview()
    @Composable
    fun WriteActivityPreview() {
        PetitionTheme {
            Content()
        }
    }
}