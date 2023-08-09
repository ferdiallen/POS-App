package com.example.posapp.view.menu

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import  com.google.accompanist.pager.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.CategoryTemplate
import com.example.posapp.widgets.menu.MenuContentGrid
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuView(
    navController:NavController,
    addButton: () -> Unit
) {

    val viewModel:MenuViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

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

    val categoryList = remember(currentIndex.value) {
        mutableStateOf(if (currentIndex.value == 0)"Makanan" else "Minuman")
    }

    viewModel.getMenu(categoryList.value)

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

    Scaffold {
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
                            LazyVerticalGrid(columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                content = {
                                    itemsIndexed(uiState) { index, item ->
                                        MenuContentGrid(data = item,
                                            clickListener = {
                                                            addButton.invoke()
                                            },
                                            navigate = {
                                            val encodedUrl = URLEncoder.encode(item.fotoProduk,
                                                StandardCharsets.UTF_8.toString())
                                            navController.navigate(RouteApp.DetailProduk.route + "/${item.namaProduk}/${encodedUrl}/${item.deskripsi}/${item.harga}")

                                        })

                                    }
                                })
                        }

                        else -> {
                            LazyVerticalGrid(columns = GridCells.Fixed(2),
                                content = {
                                    itemsIndexed(uiState) { index, item ->
                                        val encodedUrl = URLEncoder.encode(item.fotoProduk,
                                            StandardCharsets.UTF_8.toString())
                                        MenuContentGrid(data = item,
                                            clickListener = {
                                                addButton.invoke()
                                            },navigate = {
                                            navController.navigate(RouteApp.DetailProduk.route + "/${item.namaProduk}/${encodedUrl}/${item.deskripsi}/${item.harga}")
                                        })

                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}
