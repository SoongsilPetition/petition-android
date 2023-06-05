package com.marassu.petition.view.petition_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.PetitionCategory
import com.marassu.entity.user.User
import com.marassu.petition.view.theme.Gray
import com.marassu.petition.view.theme.PetitionTheme
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.TextSub
import com.marassu.petition.view.theme.notosanskr

@Composable
fun PetitionListItem(petition: Petition, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 16.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = petition.categoryList[0].categoryName, color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Normal,
                    fontSize = 8.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Text(
                text = "D-1",
                color = TextMain,
                style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = petition.title, color = TextMain,
            style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = petition.content, color = TextMain, style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Gray)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(((petition.agreement / 280f)))
                    .height(20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.Green)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "동의: ${petition.agreement}", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Normal,
                    fontSize = 8.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Text(
                text = "비동의: ${petition.disagreement}", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Normal,
                    fontSize = 8.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PetitionListItemPreview() {
    val categoryTest = PetitionCategory("-1", "시설")
    val userTest = User("1", "안녕", "test@gmail.com", "", "")
    val petitionTest = Petition(
        id = "-1",
        title = "이것은 제목입니다.",
        content = "임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용 임시 내용",
        imageUrl = "",
        categoryList = arrayListOf(categoryTest),
        agreement = 60,
        disagreement = 10,
        user = userTest,
        createdAt = "",
        updatedAt = ""
    )
    PetitionTheme() {
        PetitionListItem(petitionTest, {})
    }
}