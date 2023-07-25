package com.example.posapp.widgets.general

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.posapp.R


@Composable
fun CustomCheckBox(
    isChecked: MutableState<Boolean>
) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(6.dp))
        .border(1.dp, Color(0xFFCDD1E0), RoundedCornerShape(6.dp))
        .background(Color.Transparent)
        .clickable {
            isChecked.value = !isChecked.value
        }
        .size(20.dp)) {
        if (isChecked.value) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_check_24),
                contentDescription = null,
                tint = MaterialTheme.colors.surface,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(10.dp)
            )
        }
    }
}