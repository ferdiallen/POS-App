package com.example.posapp.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.home.HeaderHome
import com.example.posapp.widgets.home.SearchBar
import com.example.posapp.widgets.general.MenuContent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    addButton: () -> Unit
) {
    val search = remember {
        mutableStateOf("")
    }

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

    Scaffold(
        Modifier
            .fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            Column(
                Modifier
                    .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
            ) {
                HeaderHome()
                Spacer(modifier = Modifier.height(24.dp))
                SearchBar(search)
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(
                    content = {
                        itemsIndexed(rekomendasi) { index, item ->
                            MenuContent(navController,item, fotoMakanan, index, namaMakanan, hargaMakanan) {
                                addButton.invoke()
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    })

            }
        }
    }
}




