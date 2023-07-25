package com.example.posapp.view.detail_produk

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.widgets.general.TopBar
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.detail_produk.AddRemove
import com.example.posapp.widgets.detail_produk.DeskripsiProduk
import com.example.posapp.widgets.general.AddButton

@Composable
fun DetailProdukView(
    navController: NavController
) {

    val value = remember {
        mutableStateOf(5)
    }
    val listMenu = listOf(
        R.drawable.es_teh,
        R.drawable.ayam_goyeng,
        R.drawable.ayam_goyeng
    )

    val listNamaMakanan = listOf(
        "Lemon Tea",
        "Ayam Goreng",
        "Ayam Goreng"
    )

    val harga = listOf(
        "Rp 13.000",
        "Rp 13.000",
        "Rp 13.000"
    )

   Box {
       val state = rememberScrollState()
       Scaffold(
           backgroundColor = MaterialTheme.colors.background,
           modifier = Modifier
               .padding(bottom = 12.dp)
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
//                        .verticalScroll(state)
               ) {
                   TopBar(
                       navController = navController,
                       title = "Detail Produk",
                       color = Color.Transparent,
                       modifier = Modifier
                           .padding(start = 18.dp, end = 18.dp, top = 8.dp)
                   )
                   Spacer(modifier = Modifier.height(10.dp))
                   Box {
                       Surface(
                           Modifier
                               .fillMaxWidth()
                               .height(170.dp),
                           color = Color.Transparent
                       ) {
                           Image(
                               painter = painterResource(id = R.drawable.ayam_hd),
                               contentDescription = null,
                               contentScale = ContentScale.Crop
                           )
                       }
                       Column(
                           Modifier
                               .fillMaxWidth()
                       ) {
                           Box(
                               modifier =
                               Modifier
                                   .height(160.dp)
                                   .background(Color.Transparent)
                           )
                           Surface(
                               shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .fillMaxHeight(),
                               elevation = 8.dp,
                               color = MaterialTheme.colors.background
                           ) {
                               Column (
                                   Modifier
                                       .padding(top = 12.dp, end = 16.dp, start = 16.dp, bottom = 12.dp)
                                       ) {
                                   Row(
                                       Modifier
                                           .fillMaxWidth(),
                                       verticalAlignment = Alignment.CenterVertically,
                                       horizontalArrangement = Arrangement.SpaceBetween
                                   ) {
                                       Column {
                                           Text(text = "Ayam Goreng",
                                               style = MaterialTheme.typography.h1,
                                               color = MaterialTheme.colors.surface,
                                               fontSize = 14.sp)
                                           Text(text = "Rp. 13.000",
                                               color = MaterialTheme.colors.secondary,
                                               style = MaterialTheme.typography.body2,
                                               fontSize = 14.sp)
                                       }
                                       AddRemove(value)
                                   }
                                   Spacer(modifier = Modifier.height(14.dp))
                                   DeskripsiProduk()
                                   Spacer(modifier = Modifier.height(14.dp))
                                   Text(text = "Rekomendasi",
                                       style = MaterialTheme.typography.h1,
                                       color = MaterialTheme.colors.surface,
                                       fontSize = 14.sp)
                                   Spacer(modifier = Modifier.height(2.dp))
                                   LazyRow(content = {
                                       itemsIndexed(listMenu) {index,image ->
                                           RekomendasiItem(image, listNamaMakanan, index, harga)
                                           Spacer(modifier = Modifier.width(10.dp))

                                       }
                                   })

                               }

                           }
                       }
                   }
               }
           }
           Box(
               Modifier
                   .fillMaxHeight()
                   .wrapContentHeight(Alignment.Bottom)
                   .padding(start = 18.dp, end = 18.dp)
           ) {
               Button(onClick = {

               },
                   modifier = Modifier
                       .fillMaxWidth(),
                   shape = RoundedCornerShape(5.dp),
                   colors = ButtonDefaults.buttonColors(
                       backgroundColor = MaterialTheme.colors.primary
                   )
               ) {
                   Icon(painter = painterResource(id = R.drawable.cart),
                       contentDescription = null,
                        tint = MaterialTheme.colors.onSurface)
                   Spacer(modifier = Modifier.height(2.dp))
                   Text(text = "Add",
                       style = MaterialTheme.typography.body2,
                       color = MaterialTheme.colors.onSurface,
                       fontSize = 14.sp,
                       modifier = Modifier
                   )
               }
           }
       }
   }

}

@Composable
fun RekomendasiItem(
    image: Int,
    listNamaMakanan: List<String>,
    index: Int,
    harga: List<String>
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .width(100.dp)
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = listNamaMakanan[index],
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 8.sp
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = harga[index],
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 7.sp
                    )
                }
                AddButton {

                }
            }
        }
    }
}


