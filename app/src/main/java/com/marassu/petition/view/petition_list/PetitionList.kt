package com.marassu.petition.view.petition_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.marassu.entity.petition.Petition
import timber.log.Timber

@Composable
fun PetitionList(petitions: LazyPagingItems<Petition>,
                 modifier: Modifier = Modifier,
                 isBottomPaddingEnabled: Boolean = true
) {
    when(petitions.loadState.refresh) {
        is LoadState.Error -> {
            Timber.e("error")
        }
        else -> {
            val padding = if(isBottomPaddingEnabled) {
                PaddingValues(bottom = 44.dp)
            } else {
                PaddingValues()
            }
            LazyColumn(modifier = modifier,
                contentPadding = padding
            ) {
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