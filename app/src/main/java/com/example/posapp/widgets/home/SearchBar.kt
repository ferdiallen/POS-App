package com.example.posapp.widgets.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.R

@Composable
fun SearchBar(search: MutableState<String>) {
    OutlinedTextField(
        value = search.value, onValueChange = { search.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFE5E5E5),
            leadingIconColor = MaterialTheme.colors.secondary,
            errorLabelColor = MaterialTheme.colors.secondary,
            textColor = Color(0xFF454545),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colors.secondary,
            unfocusedLabelColor = MaterialTheme.colors.secondary
        ),
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.search), null)
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = "Cappucino, Air Mineral",
                style = MaterialTheme.typography.h2,
                fontSize = 14.sp
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}
