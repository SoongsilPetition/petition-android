package com.marassu.petition.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.entity.petition.Petition
import com.marassu.petition.view.petition_list.PetitionListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        PetitionList(petitions = viewModel.getPetition().collectAsLazyPagingItems())
    }
}
@Composable
fun PetitionList(petitions: LazyPagingItems<Petition>,
                 modifier: Modifier = Modifier) {
    when(petitions.loadState.refresh) {
        is LoadState.Error -> {
            Timber.e("error")
        }
        else -> {
            LazyColumn(modifier = modifier,
                contentPadding = PaddingValues(bottom = 44.dp)) {
                items(count = petitions.itemCount) {index ->
                    Timber.d("index : $index")
                    val item = petitions[index]
                    if (item != null) {
                        PetitionListItem(petition = item) {
                            Timber.d("clicked")
                        }
                        Divider(color = Color.LightGray, thickness = 1.dp)
                    }
                }
            }
        }
    }
}