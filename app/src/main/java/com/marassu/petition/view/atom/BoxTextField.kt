package com.marassu.petition.view.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marassu.petition.view.theme.Gray
import com.marassu.petition.view.theme.HintColor
import com.marassu.petition.view.theme.TextMain
import com.marassu.petition.view.theme.TextSub
import com.marassu.petition.view.theme.notosanskr

data class BoxTextFieldState(
    private val value: String = "",
    private val hint: String = "",
    private val label: String? = null
) {
    var valueState by mutableStateOf(value)
    var hintState by mutableStateOf(hint)
    var labelState by mutableStateOf(label)

    companion object {
        val Saver = run {
            val valueKey = "value"
            val hintKey = "hint"
            val labelKey = "label"

            mapSaver(
                save = {
                    mapOf(
                        valueKey to it.valueState,
                        hintKey to it.hintState,
                        labelKey to it.labelState
                    )
                },
                restore = {
                    BoxTextFieldState(
                        it[valueKey] as String,
                        it[hintKey] as String,
                        it[labelKey] as String?
                    )
                }
            )
        }
    }

}

@Composable
fun rememberBoxTextFieldState(
    value: String = "",
    hint: String = "",
    label: String? = null
): BoxTextFieldState =
    rememberSaveable(value, hint, label, saver = BoxTextFieldState.Saver) {
        BoxTextFieldState(value, hint, label)
    }

@Composable
fun BoxTextField(
    modifier: Modifier = Modifier,
    onValueChange: (data: String) -> Unit = {},
    textSize: TextUnit = 12.sp,
    isEmail: Boolean = false,
    isPassword: Boolean = false,
    state: BoxTextFieldState
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(Gray)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(0.65f),
            value = state.valueState,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = TextStyle(
                fontFamily = notosanskr,
                fontWeight = FontWeight.Normal,
                fontSize = textSize,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = TextMain,
                backgroundColor = Color.Transparent,
                cursorColor = TextMain,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = TextMain
            ),
            placeholder = {
                Text(
                    text = state.hintState, color = Color.Gray, style = TextStyle(
                        fontFamily = notosanskr,
                        fontWeight = FontWeight.Normal,
                        fontSize = textSize,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        )
        if (isEmail) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "@soongsil.ac.kr", color = Color.Gray, style = TextStyle(
                    fontFamily = notosanskr,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    }
}

@Preview
@Composable
fun BoxTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    val boxTextFieldState = rememberBoxTextFieldState(
        value = text,
        label = "이메일"
    )
    BoxTextField(onValueChange = { text = it }, state = boxTextFieldState, textSize = 12.sp)
}

@Preview
@Composable
fun BoxTextFieldEmailPreview() {
    var text by remember { mutableStateOf("") }
    val boxTextFieldState = rememberBoxTextFieldState(
        value = text,
        label = "이메일"
    )
    BoxTextField(
        onValueChange = { text = it },
        state = boxTextFieldState,
        textSize = 12.sp,
        isEmail = true
    )
}