package com.example.posapp.widgets.general

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormTelphone(
    nomor: MutableState<TextFieldValue>
) {
    Column {
        Box(modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)) {
            Text("No. Handphone",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface,
                fontSize = 10.sp)
        }
        Row(
            Modifier
                .padding(start = 18.dp)
        ) {
            Surface(
                elevation = 8.dp,
                shape = RoundedCornerShape(5.dp),
                color = MaterialTheme.colors.background

            ) {
                Text(
                    text = "62",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF6B6B6B),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(
                            start = 22.dp,
                            end = 22.dp,
                            top = 18.dp,
                            bottom = 18.dp
                        )
                )
            }
            ElevationTextField(
                string = nomor.value,
                mutable = {
                    nomor.value = it
                },
                keyboardType = KeyboardType.Number,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
