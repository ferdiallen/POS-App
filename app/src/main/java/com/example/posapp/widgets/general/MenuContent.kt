package com.example.posapp.widgets.general

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.data.CheckoutModel
import com.example.posapp.data.ProductEntity
import com.example.posapp.data.toCheckOutModel
import com.example.posapp.utils.RouteApp
import com.example.posapp.viewmodels.CheckoutViewModel
import com.example.posapp.widgets.menu.MenuContentGrid
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun MenuContent(
    navController: NavController,
    item: String,
    listProduct: List<ProductEntity>
) {

    val checkoutViewModel:CheckoutViewModel = hiltViewModel()

    Column {
        Text(
            text = item,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(7.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listProduct.forEachIndexed { index, item ->
                MenuContentGrid(data = item,
                    onAddProduct = {
                        checkoutViewModel.insertCheckout(
                          item.toCheckOutModel()
                        )
                    },
                    navigate = {
                        val encodedUrl = URLEncoder.encode(item.fotoProduk,
                            StandardCharsets.UTF_8.toString())
                        navController.navigate(RouteApp.DetailProduk.route + "/${item.namaProduk}/${encodedUrl}/${item.deskripsi}/${item.harga}")

                    })

//                Surface(
//                    shape = RoundedCornerShape(10.dp),
//                    color = MaterialTheme.colors.background,
//                    elevation = 6.dp,
//                    modifier = Modifier
//                        .width(150.dp),
//                    onClick = {
//                        navController.navigate(RouteApp.DetailProduk.route)
//                    }
//                ) {
//                    Column {
//                        Box(
//                            modifier = Modifier
//                                .height(86.dp)
//                                .fillMaxWidth()
//                        ) {
//                            Image(
//                                painter = painterResource(id = itemMakan),
//                                contentDescription = null,
//                                contentScale = ContentScale.Crop
//                            )
//                        }
//                        Spacer(modifier = Modifier.height(3.dp))
//                        Row(
//                            Modifier
//                                .padding(start = 10.dp, end = 1.dp)
//                                .fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Column {
//                                Text(
//                                    text = namaMakanan[index][indexMakan],
//                                    style = MaterialTheme.typography.h1,
//                                    color = MaterialTheme.colors.surface,
//                                    fontSize = 10.sp
//                                )
////                                                    Spacer(modifier = Modifier.height(2.dp))
//                                Text(
//                                    text = hargaMakanan[0][indexMakan],
//                                    style = MaterialTheme.typography.body1,
//                                    color = MaterialTheme.colors.secondary,
//                                    fontSize = 8.sp
//                                )
//                            }
//                            Surface(
//                                onClick = {
//                                    clickListener.invoke()
//                                },
//                                color = MaterialTheme.colors.primary,
//                                shape = RoundedCornerShape(6.dp)
//                            ) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.plus),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .padding(8.dp)
//                                        .size(8.dp)
//                                )
//                            }
//                        }
//                    }
//                }

            }
        }
    }
}