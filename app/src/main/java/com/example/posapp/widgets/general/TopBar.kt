package com.example.posapp.widgets.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.R


@Composable
fun TopBar(navController: NavController, title: String, color: Color,modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.back_circle),
                contentDescription = null,
                tint = MaterialTheme.colors.surface
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface,
            fontSize = 14.sp,
//                modifier = Modifier
//                    .offset(x=-18.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.deleteicon),
            contentDescription = null,
            tint = color
        )
    }
}
