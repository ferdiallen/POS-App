package com.example.posapp.widgets.general

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DataItem(
    order:String,
    keterangan:String,
    waktu:String,
    harga:String
) {
    Surface(
        Modifier
            .padding(start = 18.dp, end = 18.dp),
        color = MaterialTheme.colors.background,
        elevation = 6.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = order,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.surface,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = keterangan,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.surface,
                    fontSize = 12.sp
                )
            }

            Column {
                Text(
                    text = waktu,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.surface,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = harga,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.surface,
                    fontSize = 12.sp
                )
            }
        }
    }
}