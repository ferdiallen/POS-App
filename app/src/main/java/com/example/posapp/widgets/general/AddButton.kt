package com.example.posapp.widgets.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.posapp.R

@Composable
fun AddButton(clickListener: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable {
                clickListener.invoke()
            }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(8.dp)
        )
    }
}