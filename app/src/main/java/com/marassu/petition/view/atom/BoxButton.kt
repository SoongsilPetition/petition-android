package com.marassu.petition.view.atom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marassu.petition.view.theme.notosanskr

@Composable
fun BoxButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, color = Color.White, style = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}