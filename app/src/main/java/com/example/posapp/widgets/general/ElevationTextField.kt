package com.example.posapp.widgets.general

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun ElevationTextField(
    string: TextFieldValue,
    mutable: (TextFieldValue) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    minHeight: Int = 1,
    modifier: Modifier = Modifier,
    isSingle:Boolean = true
) {
    Surface(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(5.dp)
    ) {

        OutlinedTextField(
            value = string,
            onValueChange = { mutable.invoke(it) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.background,
                textColor = Color(0xFF6B6B6B)
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = modifier,
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            minLines = minHeight,


            )
    }

}