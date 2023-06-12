package com.marassu.petition.view.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.marassu.petition.R
import com.marassu.petition.view.theme.Selected
import com.marassu.petition.view.theme.TextSub
import com.marassu.petition.view.theme.notosanskr

@Composable
fun TitleTopBar(
    subTitle: String = "",
    title: String = "",
    isSearch: Boolean = false,
    isWrite: Boolean = false,
    onSearchClick: () -> Unit = {},
    onWriteClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(
                text = subTitle, color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Text(
                text = title, color = Color.Black, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
        Row() {
            if (isWrite) {
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable(onClick = onWriteClick),
                    imageVector = ImageVector.vectorResource(id = R.drawable.plus),
                    contentDescription = null,
                    tint = Selected
                )
                Spacer(modifier = Modifier.width(24.dp))
            }
            if (isSearch) {
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable(onClick = onSearchClick),
                    imageVector = ImageVector.vectorResource(id = R.drawable.search),
                    contentDescription = null,
                    tint = Selected
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TitleTopBarSearchPreview() {
    TitleTopBar(
        subTitle = "숭실대학교",
        title = "마라슈",
        isWrite = true,
        isSearch = true,
        onSearchClick = {})
}

@Preview(showBackground = true)
@Composable
fun TitleTopBarPreview() {
    TitleTopBar(title = "마라슈", isSearch = false)
}