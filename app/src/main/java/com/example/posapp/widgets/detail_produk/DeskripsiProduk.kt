package com.example.posapp.widgets.detail_produk

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeskripsiProduk(
    deskripsi:String
) {
    Text(
        text = "Deskripsi Produk",
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.surface,
        fontSize = 14.sp
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = deskripsi,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.surface,
        fontSize = 10.sp
    )
}