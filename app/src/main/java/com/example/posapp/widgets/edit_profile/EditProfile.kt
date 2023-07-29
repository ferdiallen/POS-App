package com.example.posapp.widgets.edit_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.widgets.general.ElevationTextField


@Composable
fun CustomForm(
    mutableString: MutableState<TextFieldValue>,
    desc:String
) {
    Column {
        Box(modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)) {
            Text(desc,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface,
                fontSize = 10.sp)
        }
        ElevationTextField(string = mutableString.value ,
            mutable = {
                mutableString.value = it
            },
            keyboardType = KeyboardType.Password,
            modifier = Modifier
                .fillMaxWidth())
    }
}