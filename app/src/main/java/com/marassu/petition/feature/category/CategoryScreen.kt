package com.marassu.petition.feature.category

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marassu.entity.petition.PetitionCategory
import com.marassu.petition.feature.category_petition_list.CategoryPetitionListActivity
import com.marassu.petition.view.atom.TitleTopBar

@Composable
fun CategoryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val viewModel: CategoryViewModel = hiltViewModel()
        viewModel.getPetitionCategory()
        CategoryList(categoryList = viewModel.categoryList)
    }
}



@Composable
fun CategoryList(categoryList: List<PetitionCategory>) {
    val context = LocalContext.current
    Scaffold(topBar = {
        TitleTopBar(title = "카테고리")
    }) {paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            LazyColumn() {
                items(categoryList) { item ->
                    CategoryListItem(category = item) {
                        val intent = Intent(context, CategoryPetitionListActivity::class.java)
                        intent.putExtra(CategoryPetitionListActivity.CATEGORY_NAME, item.categoryName)
                        context.startActivity(intent)
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray))
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewCategoryList() {
    val list = listOf(
        PetitionCategory("졸업"),
        PetitionCategory("시설"),
        PetitionCategory("개선"))
    CategoryList(categoryList = list)
}