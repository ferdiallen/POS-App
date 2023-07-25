package com.example.posapp.widgets.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderSum(
    keterangan: String,
    nominal:String
) {
    Surface(
        Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        color = MaterialTheme.colors.background,
        border = BorderStroke((0.2).dp, Color(0xFF979797)),
        elevation = 8.dp
    ) {
        Row(
            Modifier
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = keterangan,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.surface,
                fontSize = 14.sp
            )

            Text(
                text = nominal,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.surface,
                fontSize = 14.sp
            )
        }
    }
}