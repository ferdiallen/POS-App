package com.example.posapp.view.order

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.viewmodels.CheckoutViewModel
import com.example.posapp.widgets.general.TopBar
import com.example.posapp.widgets.order.ItemOrder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderView(
    navController: NavController
) {

    val checkoutViewModel:CheckoutViewModel = hiltViewModel()
    val uiState = checkoutViewModel.uiState.collectAsState().value

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
                Button(onClick = {
                                 navController.navigate(RouteApp.PesananSukses.route)
                },
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
                    itemsIndexed(uiState) { index, item ->
                        val value = remember {
                            mutableStateOf(index + 1)
                        }
                        ItemOrder(item ,index, value)
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



