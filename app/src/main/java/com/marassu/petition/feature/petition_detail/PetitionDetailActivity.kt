package com.marassu.petition.feature.petition_detail

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ButtonDefaults as ButtonDefaults3
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionCategory
import com.marassu.entity.user.User
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.theme.AgreeGray
import com.marassu.petition.view.theme.CategoryGray
import com.marassu.petition.view.theme.Gray
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.TextNickName
import com.marassu.petition.view.theme.TextSub
import com.marassu.petition.view.theme.notosanskr
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.count
import timber.log.Timber
import kotlin.properties.Delegates

@AndroidEntryPoint
class PetitionDetailActivity : BaseActivity() {
    var petitionId by Delegates.notNull<Long>()

    companion object {
        const val PETITON_ID = "PETITION_ID"
    }

    @Composable
    override fun Content() {
        petitionId = intent.extras?.getLong(PetitionDetailActivity.PETITON_ID, -1) ?: -1
        Detail()
    }

    @SuppressLint("SimpleDateFormat")
    @Composable
    fun Detail() {
        val viewModel: PetitionDetailViewModel = hiltViewModel()
        val updateFlag = viewModel.updatePetitionFlag.collectAsState()
        val updateConcurFlag = viewModel.updateConcurFlag.collectAsState()

        viewModel.loadPetition(petitionId = petitionId)
        if (updateFlag.value == 1) {
            val concurs = viewModel.getConcur().collectAsLazyPagingItems()
            Scaffold(topBar = {
                TopBar(isBack = true, onLeftClick = {})
            }) { paddingValues ->
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(paddingValues)
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        LazyColumn {
                            item {
                                viewModel.petition?.let {
                                    PetitionBody(
                                        petition = it,
                                        onToggleClick = { viewModel.updateAgreement(it) })
                                }
                            }
                            if (updateConcurFlag.value == 1) {
                                when (concurs.loadState.refresh) {
                                    is LoadState.Error -> {
                                        Timber.e("error")
                                    }

                                    else -> {
                                        items(count = concurs.itemCount) {
                                            concurs[it]?.let { it1 -> ConcurItem(concur = it1) }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PetitionBody(petition: Petition, onToggleClick: (index: Int) -> Unit) {
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
        val defaultConcur = remember { mutableStateOf("동의합니다") }
        val concur by defaultConcur
        Text(
            text = petition.title,
            color = TextMain,
            style = TextStyle(
                fontFamily = notosanskr,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            LazyRow() {
                items(petition.categoryList) { item ->
                    Box(Modifier.background(CategoryGray)) {
                        Text(
                            text = item.categoryName,
                            color = TextMain,
                            style = TextStyle(
                                fontFamily = notosanskr,
                                fontSize = 10.sp,
                                background = CategoryGray,
                                platformStyle = PlatformTextStyle(includeFontPadding = false)
                            ),
                            modifier = Modifier.padding(
                                start = 11.dp,
                                end = 11.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            )
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                            .background(Color.White)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 12.dp)
        ) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            Text(
                text = petition.user.name,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = notosanskr,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Spacer(Modifier.weight(1f))
            Text(
                //TODO 시스템에서 불러온 날짜 적용하기
                text = "2023/04/01 - 2023/06/10",
                color = TextSub,
                style = TextStyle(
                    fontFamily = notosanskr,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = petition.content,
                color = TextMain,
                style = TextStyle(
                    fontFamily = notosanskr,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }

        //TODO Image 사이즈 반영
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
        ) {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Gray)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(((petition.agreement / 280f)))
                    .height(20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.Green)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "동의: ${petition.agreement}", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Normal,
                    fontSize = 8.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Text(
                text = "비동의: ${petition.disagreement}", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Normal,
                    fontSize = 8.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
        ) {

            val cornerRadius = 5.dp
            var selectedIndex by remember { mutableStateOf(0) }

            val itemsList = listOf("동의", "비동의")
            itemsList.forEachIndexed { index, item ->

                OutlinedButton(
                    onClick = {
                        selectedIndex = index
                        if (selectedIndex == 0) {
                            defaultConcur.value = "동의합니다."
                        } else {
                            defaultConcur.value = "비동이합니다."
                        }
                        onToggleClick(index)
                    },
                    modifier = when (index) {
                        0 ->
                            Modifier
                                .weight(1f)
                                .offset(0.dp, 0.dp)
                                .zIndex(if (selectedIndex == index) 1f else 0f)

                        else ->
                            Modifier
                                .weight(1f)
                                .offset((-1 * index).dp, 0.dp)
                                .zIndex(if (selectedIndex == index) 1f else 0f)
                    },
                    border = BorderStroke(0.dp, Color.Transparent),
                    shape = when (index) {
                        0 -> RoundedCornerShape(
                            topStart = cornerRadius,
                            topEnd = 0.dp,
                            bottomStart = cornerRadius,
                            bottomEnd = 0.dp
                        )

                        itemsList.size - 1 -> RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = cornerRadius,
                            bottomStart = 0.dp,
                            bottomEnd = cornerRadius
                        )

                        else -> RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    },
                    colors = if (selectedIndex == index) {
                        if (selectedIndex == 1) {
                            ButtonDefaults3.outlinedButtonColors(
                                containerColor = Red,
                                contentColor = Color.White
                            )
                        } else {
                            ButtonDefaults3.outlinedButtonColors(
                                containerColor = Green,
                                contentColor = Color(0xFF646464)
                            )

                        }
                    } else {
                        ButtonDefaults3.outlinedButtonColors(
                            containerColor = AgreeGray,
                            contentColor = Color(0xFF646464)
                        )
                    }
                ) {
                    Text("$item")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val content = remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(all = 0.dp),
                value = content.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    content.value = it
//                            onValueChange(it)
                },
                label = { Text(text = concur) }
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(AgreeGray),
                modifier = Modifier
                    .border(
                        width = 0.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(0.dp)
                    )
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                Text(text = "입력", color = Color(0xFF757575))
            }

        }
    }
}

@Composable
fun ConcurItem(concur: Concur) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(start = 24.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "익명",
            color = TextNickName,
            style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = concur.content,
            color = TextMain,
            style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Spacer(
            modifier = Modifier
                .width(12.dp)
                .height(60.dp)
                .background(
                    color = if (concur.agreementStatus == AgreementStatus.AGREE)
                        Color.Green
                    else
                        Color.Red
                )
        )
    }
}