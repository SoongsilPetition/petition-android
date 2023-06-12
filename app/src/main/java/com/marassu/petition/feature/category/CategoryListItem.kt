package com.marassu.petition.feature.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marassu.entity.petition.PetitionCategory
import com.marassu.petition.R
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.notosanskr

@Composable
fun CategoryListItem(category: PetitionCategory, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(24.dp))
        Icon(
            modifier = Modifier
                .size(12.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_category_circle_small),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = category.categoryName,
            color = TextMain,
            style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )

    }
}

@Composable
@Preview
fun previewCategoryListItem() {
    val category = PetitionCategory("졸업")
    CategoryListItem(category = category) {
    }
}