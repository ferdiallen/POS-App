package com.example.posapp.view.data_penjualan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.posapp.widgets.general.DataItem
import com.example.posapp.widgets.general.DatePicker
import com.example.posapp.widgets.general.OrderSum
import com.example.posapp.widgets.general.TopBar

@Composable
fun DataPenjualan(
    navController: NavController
) {

    val context = LocalContext.current
    val dateValue = remember {
        mutableStateOf("")
    }
    val showDialog = remember {
        mutableStateOf(false)
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
       Box(modifier = Modifier) {
           Column {
               Box(
                   modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp, bottom = 8.dp, top = 8.dp)
               ) {
                   TopBar(
                       navController = navController,
                       title = "Data Penjualan",
                       color = Color.Transparent
                   )
               }
               Spacer(modifier = Modifier.height(10.dp))
               Box(
                   modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp, bottom = 8.dp, top = 8.dp)
               ) {
                   DatePicker(
                       dateValue = dateValue,
                       showDialog = showDialog,
                       context = context
                   ) {

                   }
               }
               Spacer(modifier = Modifier.height(20.dp))
               LazyColumn(content = {
                   items(24) {
                       DataItem(order = "Order #240",
                           keterangan = "Jumlah Pesanan  : 8" ,
                           waktu ="18.00" ,
                           harga = "Rp. 80.000" )
                       Spacer(modifier = Modifier.height(if (it == 23) 62.dp else 5.dp))
                   }

               })


           }
       }
        Box(modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Bottom)) {
            OrderSum(
                "Total Pemasukan",
                "Rp. 1.280.000"
            )
        }
    }
}




