package com.example.posapp.widgets.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.posapp.data.LoginModel

@Composable
fun HeaderHome(loginData: LoginModel?) {
    Box {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hi ${loginData?.username}",
                style = MaterialTheme.typography.h1,
                fontSize = 14.sp,
                color = MaterialTheme.colors.surface
            )

            Surface(
                Modifier
                    .size(35.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                AsyncImage(
                    model = loginData?.profilePicture,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
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