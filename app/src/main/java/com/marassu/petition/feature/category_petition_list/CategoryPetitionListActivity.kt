package com.marassu.petition.feature.category_petition_list

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.State
import androidx.paging.PagingData
import com.marassu.entity.petition.Petition
import com.marassu.petition.view.atom.LoadingAnimation
import kotlinx.coroutines.flow.Flow

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.entity.petition.PetitionCategory
import com.marassu.entity.user.User
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.feature.petition_detail.PetitionDetailActivity
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.petition_list.PetitionList
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.notosanskr
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@AndroidEntryPoint
class CategoryPetitionListActivity : BaseActivity() {
    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }

    @Composable
    override fun Content() {
        val viewModel: CategoryPetitionListViewModel = hiltViewModel()
        var text = viewModel.buttonText.collectAsState()
        val myText by text
        val category = intent.getStringExtra(CATEGORY_NAME) ?: ""
        val refreshState =
            viewModel.refreshStateFlow.collectAsState(initial = CategoryPetitionListViewModel.Event.Refresh)
        CategoryPetitionList(
            name = myText,
            category = category,
            refreshState = refreshState,
            updateAction = { viewModel.update() },
            getPetitionAction = { viewModel.getPetition(category) },
            onClickItem = { petition ->
                val detailIntent: Intent =
                    Intent(this, PetitionDetailActivity::class.java)
                detailIntent.putExtra(
                    PetitionDetailActivity.PETITON_ID,
                    petition.id
                )
                startActivity(detailIntent)
            })
    }
}

@Composable
fun CategoryPetitionList(
    name: String,
    category: String,
    refreshState: State<CategoryPetitionListViewModel.Event>,
    updateAction: () -> Unit,
    getPetitionAction: () -> Flow<PagingData<Petition>>,
    onClickItem: (Petition) -> Unit
) {
    Scaffold(topBar = {
        CategoryPetitionTopBar(text = name, onClick = {
            updateAction()
        }, title = category)
    }) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (refreshState.value) {
                is CategoryPetitionListViewModel.Event.Refresh -> {
                    PetitionList(
                        petitions = getPetitionAction().collectAsLazyPagingItems(),
                        isBottomPaddingEnabled = false,
                        onClick = { petition -> onClickItem(petition) }
                    )
                }

                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadingAnimation()
                    }
                }
            }

        }
    }
}

@Composable
fun CategoryPetitionTopBar(onClick: () -> Unit, text: String, title: String) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(title = title, isBack = true, onLeftClick = {
            (context as Activity).finish()
        })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(all = 0.dp)
                .height(32.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                elevation = null,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = text,
                    color = TextMain,
                    style = TextStyle(
                        fontFamily = notosanskr,
                        fontSize = 8.sp,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun previewCategoryPetitionList() {
    val refreshState = mutableStateOf(CategoryPetitionListViewModel.Event.Refresh)


    val categoryTest = PetitionCategory("시설")
    val userTest = User("1", "안녕", "test@gmail.com", "", "")
    val petitionTest = Petition(
        id = -1,
        title = "이것은 제목입니다.",
        content = "임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용",
        imageUrl = "",
        categoryList = arrayListOf(categoryTest),
        agreement = 60,
        disagreement = 10,
        user = userTest,
        createdAt = "",
        updatedAt = "",
        dueDate = ""
    )
    CategoryPetitionList(
        name = "최근정렬순",
        category = "졸업",
        refreshState = refreshState,
        updateAction = { },
        getPetitionAction = { flow { emit(PagingData.from(listOf(petitionTest))) } },
        onClickItem = { }
    )
}

