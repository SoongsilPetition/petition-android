package com.marassu.petition.view.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.entity.petition.Petition
import com.marassu.petition.feature.petition_detail.PetitionDetailActivity
import com.marassu.petition.view.atom.TitleTopBar
import com.marassu.petition.view.petition_list.PetitionList
import com.marassu.petition.view.petition_list.PetitionListItem
import timber.log.Timber

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        val context = LocalContext.current
        Scaffold(topBar = {
            TitleTopBar(subTitle = "숭실대학교", title = "마라슈", isSearch = true, onSearchClick = {})
        }) { paddingValues ->
            Surface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                PetitionList(petitions = viewModel.getPetition().collectAsLazyPagingItems(),
                onClick = {petition ->
                    val detailIntent: Intent = Intent(context, PetitionDetailActivity::class.java)
                    detailIntent.putExtra(PetitionDetailActivity.PETITON_ID, petition.id.toLong())
                    context.startActivity(detailIntent)
                })
            }
        }
    }
}