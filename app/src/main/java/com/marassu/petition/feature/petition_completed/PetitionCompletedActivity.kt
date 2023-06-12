package com.marassu.petition.feature.petition_completed

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition_answer.PetitionAnswer
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.theme.AgreeGray
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.TextNickName
import com.marassu.petition.view.theme.TextSub
import com.marassu.petition.view.theme.notosanskr
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class PetitionCompletedActivity : BaseActivity() {
    var petitionId by Delegates.notNull<Long>()

    companion object {
        const val PETITON_ID = "PETITION_ID"
    }

    @Composable
    override fun Content() {
        petitionId = intent.extras?.getLong(PETITON_ID, -1) ?: -1
        Detail()
    }

    @Composable
    fun Detail() {
        val viewModel: PetitionCompletedViewModel = hiltViewModel()
        val updateFlag = viewModel.updatePetitionFlag.collectAsState()
        viewModel.loadPetition(petitionId = petitionId)
        if (updateFlag.value == 1) {
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
                                viewModel.petitionAnswer?.let {
                                    PetitionBody(
                                        petitionAnswer = it,
                                        onToggleClick = { toggle -> viewModel.updateAgreement(toggle) },
                                        onClick = { content ->
                                        })
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
fun PetitionBody(
    petitionAnswer: PetitionAnswer,
    onToggleClick: (index: Int) -> Unit,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
        val defaultConcur = remember { mutableStateOf("동의합니다") }
        val concur by defaultConcur
        Text(
            text = petitionAnswer.title,
            color = TextMain,
            style = TextStyle(
                fontFamily = notosanskr,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 12.dp)
        ) {
            Text(
                text = petitionAnswer.user.name,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = notosanskr,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Spacer(Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = petitionAnswer.content,
                color = TextMain,
                style = TextStyle(
                    fontFamily = notosanskr,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
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