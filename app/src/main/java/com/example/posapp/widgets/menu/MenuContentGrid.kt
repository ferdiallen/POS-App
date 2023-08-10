package com.example.posapp.widgets.menu

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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.posapp.R
import com.example.posapp.data.ProductEntity
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.AddButton

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun MenuContentGrid(
    fotoMakanan: List<List<Int>>,
    index: Int,
    namaMakanan: List<List<String>>,
    hargaMakanan: List<List<String>>,
    navController: NavController,
    show: Boolean = true,
    route: String = RouteApp.DetailProduk.route,
    clickListener: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        fotoMakanan[index].forEachIndexed { indexMakan, itemMakan ->
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colors.background,
                elevation = 6.dp,
                modifier = Modifier
                    .width(150.dp),
                onClick = {
                    navController.navigate(route)

                }
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(86.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = itemMakan),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Row(
                        Modifier
                            .padding(start = 10.dp, end = 1.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = namaMakanan[index][indexMakan],
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.surface,
                                fontSize = 10.sp
                            )
//                                                    Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = hargaMakanan[0][indexMakan],
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.secondary,
                                fontSize = 8.sp
                            )
                        }
                        if (show) {
                            AddButton(clickListener)
                        }
                    }
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuContentGrid(
    data: ProductEntity,
    onAddProduct: () -> Unit,
    navigate: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.background,
        elevation = 6.dp,
        modifier = Modifier
            .width(150.dp),
        onClick = {
            navigate.invoke("")

        }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(86.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = data.fotoProduk,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Row(
                Modifier
                    .padding(start = 10.dp, end = 1.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = data.namaProduk,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 10.sp
                    )
//                                                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${data.harga}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 8.sp
                    )
                }
                AddButton {
                onAddProduct.invoke()
                }
            }
        }
    }
}

