package com.example.posapp.view.add_menu_produk

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import  com.google.accompanist.pager.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.CategoryTemplate
import com.example.posapp.widgets.menu.MenuContentGrid

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddMenuProdukView(
    navController:NavController,
    addButton: () -> Unit
) {

    val category = listOf(
        "Makanan",
        "Minuman"
    )
    val currentSelected = remember {
        mutableStateOf(false)
    }

    val currentIndex = remember {
        mutableStateOf(0)
    }

    val pagerState = com.google.accompanist.pager.rememberPagerState()

    val rekomendasi = listOf(
        "Rekomendasi 1",
        "Rekomendasi 2",
        "Rekomendasi 3",
        "Rekomendasi 4",
        "Rekomendasi 5"
    )

    val namaMakanan = listOf(
        listOf(
            "Ayam Goreng",
            "Es Teh Manis"
        ),
        listOf(
            "Ayam Goreng",
            "Air Mineral"
        ),
        listOf(
            "Ayam Goreng",
            "Es Teh Manis"
        ),
        listOf(
            "Ayam Goreng",
            "Air Mineral"
        ),
        listOf(
            "Ayam Goreng",
            "Air Mineral"
        )
    )

    val hargaMakanan = listOf(
        listOf(
            "Rp. 13.000",
            "Rp. 5.000"
        ),
    )

    val coroutine = rememberCoroutineScope()

    val fotoMakanan = listOf(
        listOf(
            R.drawable.ayam_goyeng,
            R.drawable.es_teh
        ),
        listOf(
            R.drawable.nasi_goyeng,
            R.drawable.air_putih
        ),
        listOf(
            R.drawable.nasi_goyeng,
            R.drawable.air_putih
        ),
        listOf(
            R.drawable.ayam_goyeng,
            R.drawable.es_teh
        ),
        listOf(
            R.drawable.nasi_goyeng,
            R.drawable.air_putih
        ),

        )

    Scaffold (
        bottomBar = {
            Box(modifier = Modifier
                .padding(start = 18.dp,end = 18.dp)) {
                Button(onClick = { navController.navigate(RouteApp.AddFormMenu.route) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    )
                ) {
                    Icon(painter = painterResource(id = R.drawable.add_rounded),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Tambah Produk",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                }
            }
        }
            ) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally)
                ) {
                    Text(
                        text = "Menu Produk",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    category.forEachIndexed { index, categoryy ->
                        currentSelected.value = currentIndex.value == index
                        val color by animateColorAsState(
                            targetValue = if (currentSelected.value) MaterialTheme.colors.primary else Color(
                                0xFFE5E5E5
                            )
                        )
                        val colorFont by animateColorAsState(targetValue = if (currentSelected.value) MaterialTheme.colors.onSurface else MaterialTheme.colors.secondary)
                        CategoryTemplate(
                            color,
                            colorFont,
                            currentIndex,
                            index,
                            coroutine,
                            pagerState,
                            categoryy
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                HorizontalPager(count = category.size, state = pagerState) {
                    currentIndex.value = it
                    when (it) {
                        0 -> {
                            LazyVerticalGrid(columns = GridCells.Fixed(1),
                                content = {
                                    itemsIndexed(rekomendasi) { index, item ->
                                        MenuContentGrid(
                                            fotoMakanan = fotoMakanan,
                                            index = index,
                                            namaMakanan = namaMakanan,
                                            hargaMakanan = hargaMakanan,
                                            navController,
                                            false,
                                        ) {
//                                            addButton.invoke()
                                        }

                                    }
                                })
                        }
                        else -> {
                            LazyVerticalGrid(columns = GridCells.Fixed(1),
                                content = {
                                    itemsIndexed(rekomendasi) { index, item ->
                                        MenuContentGrid(
                                            fotoMakanan = fotoMakanan,
                                            index = index,
                                            namaMakanan = namaMakanan,
                                            hargaMakanan = hargaMakanan,
                                            navController,
                                            false
                                        ) {

                                        }

                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}
