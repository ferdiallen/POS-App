package com.example.posapp.view.data_pembelian

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.DataItem
import com.example.posapp.widgets.general.DatePicker
import com.example.posapp.widgets.general.OrderSum
import com.example.posapp.widgets.general.TopBar

@Composable
fun DataPembelianView(
    navController: NavController
) {
    val context = LocalContext.current
    val dateValue = remember {
        mutableStateOf("")
    }
    val showDialog = remember {
        mutableStateOf(false)
    }

    Box {
        Scaffold(
        ) {
            Surface(
                Modifier
                    .padding(it)
                    .fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Column {
                    Box(modifier = Modifier
                        .padding(start = 18.dp, end = 18.dp)) {
                        TopBar(navController = navController ,
                            title = "Data Pembelian" ,
                            color = Color.Transparent )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp, bottom = 8.dp, top = 8.dp)
                    ) {
                        DatePicker(dateValue = dateValue,
                            showDialog = showDialog ,
                            context = context ) {

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn(content = {
                        items(20) {
                            DataItem(order = "Pembelian #240",
                                keterangan = "Pembelian Ayam" ,
                                waktu = "18.00" ,
                                harga = "Rp. 350.000" )
                            Spacer(modifier = Modifier.height(if (it == 19) 62.dp else 5.dp))
                        }
                    })
                }
            }
        }
        Box(modifier = Modifier
            .padding(end = 18.dp)
            .fillMaxHeight()
            .wrapContentHeight(Bottom)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .offset(y = (-64).dp)) {
            FloatingActionButton(onClick = {
                                           navController.navigate(RouteApp.InputPembelian.route)
            },
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onSurface) {
                Icon(painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp))
            }
        }
        Box(
            Modifier
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
        ) {
            OrderSum(keterangan = "Total Pengeluaran :",
                nominal = "Rp. 1.280.000" )
        }
    }
}