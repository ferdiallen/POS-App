package com.example.posapp.view.transaksi

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.CategoryTemplate
import com.example.posapp.widgets.transaksi.TransaksiOrderTemplate
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun TransaksiOrder(
    navController: NavController
) {

    val category = listOf(
        "Proses",
        "Selesai"
    )

    val pagerState = rememberPagerState()
    val currentSelected = remember {
        mutableStateOf(false)
    }
    val currentIndex = remember {
        mutableStateOf(0)
    }
    val coroutine = rememberCoroutineScope()

    Scaffold (
        backgroundColor = MaterialTheme.colors.background
            ){
        Surface(Modifier
            .fillMaxSize()
            .padding(it),
            color = MaterialTheme.colors.background) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)) {
                    Text(text = "Transaksi Obat",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    category.forEachIndexed { index, s ->
                        currentSelected.value = currentIndex.value == index
                        val color by animateColorAsState(
                            targetValue = if (currentSelected.value) MaterialTheme.colors.primary else Color(
                                0xFFE5E5E5
                            )
                        )
                        val colorFont by animateColorAsState(targetValue = if (currentSelected.value) MaterialTheme.colors.onSurface else MaterialTheme.colors.secondary)
                        CategoryTemplate(
                            color = color,
                            colorFont,
                            currentIndex = currentIndex,
                            index = index,
                            coroutine = coroutine ,
                            pagerState = pagerState ,
                            categoryy = s
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                HorizontalPager(count = category.size, state = pagerState ) {
                    currentIndex.value = it
                    when(it) {
                        0 -> {
                            LazyColumn(
                                Modifier
                                    .fillMaxSize()
                                ,content = {
                                items(10) {
                                    TransaksiOrderTemplate() {
                                        navController.navigate(RouteApp.DetailOrder.route)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            })
                        }
                        else -> {
                            LazyColumn(
                                Modifier
                                    .fillMaxSize()
                                ,content = {
                                    items(10) {
                                        TransaksiOrderTemplate(){
                                            navController.navigate(RouteApp.DetailOrder.route)
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}
