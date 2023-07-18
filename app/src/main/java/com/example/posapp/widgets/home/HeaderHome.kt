package com.example.posapp.widgets.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.R

@Composable
fun HeaderHome() {
    Box {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hi Buddy",
                style = MaterialTheme.typography.h1,
                fontSize = 14.sp,
                color = MaterialTheme.colors.surface
            )

            Surface(
                Modifier
                    .size(35.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mbak_cantik),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = "Selamat Datang Di MyPOS App",
            style = MaterialTheme.typography.h1,
            fontSize = 18.sp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier
                .width(184.dp)
                .offset(y = 30.dp)
        )
    }
}