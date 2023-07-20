package com.example.posapp.widgets.detail_produk

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.R


@Composable
fun AddRemove(value: MutableState<Int>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.icon_b_minus),
            contentDescription = null,
            modifier = Modifier
                .size(21.dp)
                .clickable {
                    if (value.value > 0) {
                        value.value -= 1
                    }
                })
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colors.surface.copy(0.3f),
            elevation = 0.dp,
            modifier = Modifier
                .size(17.dp)

        ) {
            Text(
                text = value.value.toString(),
                style = MaterialTheme.typography.body2,
                color = Color(0xFF979797),
                fontSize = 8.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(1.dp))
        Image(painter = painterResource(id = R.drawable.icon_b_plus),
            contentDescription = null,
            modifier = Modifier
                .size(18.dp)
                .clickable {

                    value.value += 1

                })
    }
}