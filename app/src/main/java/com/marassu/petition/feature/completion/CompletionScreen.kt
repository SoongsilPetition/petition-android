package com.marassu.petition.feature.completion

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.petition.feature.petition_completed.PetitionCompletedActivity
import com.marassu.petition.feature.petition_completed.PetitionCompletedActivity.Companion.PETITON_ID
import com.marassu.petition.view.atom.TitleTopBar
import com.marassu.petition.view.petition_list.PetitionList

@Composable
fun CompletionScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val context = LocalContext.current
        val viewModel: CompletionViewModel = hiltViewModel()
        Scaffold(topBar = {
            TitleTopBar(title = "완료 청원")
        }) { paddingValues ->
            Surface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                PetitionList(petitions = viewModel.getPetition().collectAsLazyPagingItems(),
                    onClick = { petition ->
                        val completionIntent = Intent(context, PetitionCompletedActivity::class.java)
                        completionIntent.putExtra(PETITON_ID, petition.id)
                        completionIntent.putExtra("PETITION", petition)
                        context.startActivity(completionIntent)
                    })
            }
        }
    }

}