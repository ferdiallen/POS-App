package com.example.posapp.view.order

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderView(
    navController: NavController
) {

    val listMakanan = listOf(
        R.drawable.ayam_kotak,
        R.drawable.esteh_kotak
    )
    val namaMakanan = listOf(
        "Ayam Goreng",
        "Es Teh Manis"
    )
    val harga = listOf(
        "Rp 13.000",
        "Rp 5.000"
    )

    val catatan = remember {
        mutableStateOf("")
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            Column (
                Modifier
                    .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        Text(text = "Catatan",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.surface,
                            fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                CatatanField(catatan)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total: ",
                        style = MaterialTheme.typography.h1,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.surface)

                    Text(text = "Rp. 80.000",
                        style = MaterialTheme.typography.h3,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.surface)
                }
                Spacer(modifier = Modifier.height(10.dp))
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
            ) {
                TopBar(navController, "Pesanan", Color.Transparent)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(content = {
                    itemsIndexed(listMakanan) { index, item ->
                        val value = remember {
                            mutableStateOf(index + 1)
                        }
                        ItemOrder(item, namaMakanan, index, harga, value)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                })

            }
        }
    }
}

@Composable
private fun CatatanField(catatan: MutableState<String>) {
    OutlinedTextField(
        value = catatan.value,
        onValueChange = { catatan.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.surface,
            focusedBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            errorBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            disabledBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            unfocusedBorderColor = MaterialTheme.colors.surface.copy(0.4f),
            backgroundColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth(),
        minLines = 4,
        textStyle = TextStyle(
            fontSize = 10.sp
        )
    )
}

@Composable
fun ItemOrder(
    item: Int,
    namaMakanan: List<String>,
    index: Int,
    harga: List<String>,
    value: MutableState<Int>
) {
    Surface(
        Modifier
            .fillMaxWidth(),
        elevation = 6.dp,
        color = MaterialTheme.colors.background
    ) {
        Row(
            Modifier
                .padding(
                    start = 3.dp,
                    end = 6.dp,
                    top = 3.dp,
                    bottom = 3.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Row(
                verticalAlignment = CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Column {
                    Text(
                        text = namaMakanan[index],
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp
                    )
                    Text(
                        text = harga[index],
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 14.sp
                    )
                    Row(
                        verticalAlignment = CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.icon_b_minus),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    if (value.value > 0) {
                                        value.value -= 1
                                    }
                                })
                        Spacer(modifier = Modifier.width(1.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colors.surface.copy(0.3f),
                            elevation = 0.dp,
                            modifier = Modifier
                                .size(15.dp)

                        ) {
                            Text(
                                text = value.value.toString(),
                                style = MaterialTheme.typography.body2,
                                color = Color(0xFF979797),
                                fontSize = 8.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Center),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(1.dp))
                        Image(painter = painterResource(id = R.drawable.icon_b_plus),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {

                                    value.value += 1

                                })
                    }
                }
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.deleteicon),
                    contentDescription = null,
                    tint = Color(0xFF3F3F3F))
            }
        }
    }
}

@Composable
fun TopBar(navController: NavController, title: String,color:Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.back_circle),
                contentDescription = null,
                tint = MaterialTheme.colors.surface
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface,
            fontSize = 14.sp,
//                modifier = Modifier
//                    .offset(x=-18.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.deleteicon),
            contentDescription = null,
            tint = color
        )
    }
}