package com.marassu.petition.feature.category_petition_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.marassu.petition.base.BaseActivity
import com.marassu.petition.view.atom.TopBar
import com.marassu.petition.view.petition_list.PetitionList
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.notosanskr
import dagger.hilt.android.AndroidEntryPoint

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
        val flag = viewModel.refreshEvent.collectAsState()
        Scaffold(topBar = {
            CategoryPetitionTopBar(text = myText, onClick = {
                viewModel.update()
            })
        }) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                if(flag.value > 0) {
                    PetitionList(
                        petitions = viewModel.changeSetting(category).collectAsLazyPagingItems(),
                        isBottomPaddingEnabled = false,
                        onClick = {petition ->

                        }
                    )
                } else {
                    PetitionList(
                        petitions = viewModel.getPetition(category).collectAsLazyPagingItems(),
                        isBottomPaddingEnabled = false,
                        onClick = { petition ->

                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun previewCategoryPetitionListTopBar() {
    CategoryPetitionTopBar({}, "최근 작성 순")
}

@Composable
fun CategoryPetitionTopBar(onClick: () -> Unit, text: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(title = "청원 : 졸업", isBack = true, onLeftClick = { })
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