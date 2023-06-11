package com.marassu.petition.view.atom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun TopBar(
    title: String = "",
    isBack: Boolean = true,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isBack) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable(onClick = onLeftClick),
                imageVector = ImageVector.vectorResource(id = R.drawable.angle_left),
                contentDescription = null,
                tint = Selected
            )
        } else {
            Text(
                modifier = Modifier.clickable(onClick = onLeftClick),
                text = "닫기", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
        Text(
            text = title, color = Color.Black, style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        if (isBack) {
            Text(
                modifier = Modifier.clickable(onClick = onLeftClick),
                text = "       ", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        } else {
            Text(
                modifier = Modifier.clickable(onClick = onLeftClick),
                text = "완료", color = TextSub, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarBackPreview() {
    TopBar("청원 작성", true)
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("청원 작성", false)
}