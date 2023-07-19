package com.example.posapp.view.detail_order

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.widgets.general.TopBar

@Composable
fun DetailOrderView(
    navController: NavController
) {

    val state = rememberScrollState()

    Box {
        Scaffold (
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .padding(bottom = 46.dp)
        ) {
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colors.background
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
                        .verticalScroll(state)
                ) {
                    TopBar(navController = navController ,
                        title = " Detail Order" ,
                        color = MaterialTheme.colors.primary)
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        elevation = 8.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colors.background
                    ) {
                        Column(
                            Modifier
                                .padding(14.dp)
                        ) {
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Order #240",
                                    style = MaterialTheme.typography.h1,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 12.sp)

                                Text(text = "18:00",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Jumlah Pesanan : 8",
                                style = MaterialTheme.typography.h3,
                                color = MaterialTheme.colors.surface,
                                fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Ayam Goreng",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                                Text(text = "x5",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                                Text(text = "Rp. 13.000",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Ayam Goreng",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                                Text(text = "x5",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                                Text(text = "Rp. 13.000",
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Catatan",
                                style = MaterialTheme.typography.h3,
                                color = MaterialTheme.colors.surface,
                                fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Ayam gorengnya pakai sambal sedikit",
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.surface,
                                fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(40.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Total",
                                    style = MaterialTheme.typography.h1,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 12.sp)

                                Text(text = "Rp. 80.000",
                                    style = MaterialTheme.typography.h3,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
        Box(
            Modifier
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
                .padding(start = 18.dp, end = 18.dp)
        ) {
            Button(onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Text(text = "Buat Pesanan",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }
        }
    }
}