package com.example.posapp.widgets.general

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp


@Composable
fun OutlineTextField(
    mutableState: MutableState<String>,
    keyboarType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label:String,
    leadingIcon: @Composable () -> Unit
) {
    OutlinedTextField(
        value = mutableState.value,
        onValueChange = { mutableState.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            disabledBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            errorBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            focusedBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            textColor = MaterialTheme.colors.surface,
            trailingIconColor = MaterialTheme.colors.surface,
            unfocusedLabelColor = MaterialTheme.colors.surface,
            focusedLabelColor = MaterialTheme.colors.surface,
            errorLabelColor = MaterialTheme.colors.surface,
            disabledLabelColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier
            .fillMaxWidth(),
        trailingIcon = {
            leadingIcon.invoke()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboarType,
            imeAction = ImeAction.Done
        ),
        visualTransformation = visualTransformation,
        singleLine = true,
        label = {
            Text(text = label,
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp,
                color = MaterialTheme.colors.surface)
        }
    )
}