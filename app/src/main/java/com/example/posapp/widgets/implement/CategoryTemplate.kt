package com.example.posapp.widgets.implement

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun CategoryTemplate(
    color: Color,
    colorFont: Color,
    currentIndex: MutableState<Int>,
    index: Int,
    coroutine: CoroutineScope,
    pagerState: PagerState,
    categoryy: String
) {

    Surface(
        onClick = {
            currentIndex.value = index
            coroutine.launch {
                pagerState.scrollToPage(index)
            }
        },
        shape = RoundedCornerShape(5.dp),
        color = color,
        modifier = Modifier
            .width(156.dp)
    ) {
        Text(
            text = categoryy,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h2,
            fontSize = 14.sp,
            color = colorFont
        )
    }
}