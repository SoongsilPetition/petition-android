package com.marassu.petition.feature.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.petition.feature.petition_detail.PetitionDetailActivity
import com.marassu.petition.feature.write.WriteActivity
import com.marassu.petition.view.atom.TitleTopBar
import com.marassu.petition.view.petition_list.PetitionList

@Composable
fun HomeScreen() {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        Scaffold(topBar = {
            TitleTopBar(
                subTitle = "숭실대학교",
                title = "마라슈",
                isSearch = true,
                isWrite = true,
                onSearchClick = {},
                onWriteClick = {
                    val intent = Intent(context, WriteActivity::class.java)
                    context.startActivity(intent)
                })
        }) { paddingValues ->
            Surface(
                modifier = Modifier
                    .padding(
                        bottom = 44.dp,
                        top = paddingValues.calculateTopPadding()
                    )
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                PetitionList(petitions = viewModel.getPetition().collectAsLazyPagingItems(),
                    onClick = { petition ->
                        val detailIntent =
                            Intent(context, PetitionDetailActivity::class.java)
                        detailIntent.putExtra(
                            PetitionDetailActivity.PETITON_ID,
                            petition.id
                        )
                        context.startActivity(detailIntent)
                    },
                    isBottomPaddingEnabled = false)
            }
        }
    }
}