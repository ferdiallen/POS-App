package com.example.posapp.widgets.edit_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.widgets.general.ElevationTextField


@Composable
fun CustomForm(
    text: String,
    desc: String,
    onChange: (String) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp)
        ) {
            Text(
                desc,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface,
                fontSize = 10.sp
            )
        }
        Card(
            modifier = Modifier.padding(horizontal = 12.dp),
            backgroundColor = Color.White, elevation = 12.dp
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onChange::invoke,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}