package com.example.posapp.view.input_pembelian

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.utils.moneyFormatter
import com.example.posapp.widgets.general.ElevationTextField
import com.example.posapp.widgets.general.TopBar
import java.text.DecimalFormat

@Composable
fun InputPembelian(
    navController: NavController
) {
    val pembelian = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val harga = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val deskripsi = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val formatter = DecimalFormat("#,###")
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
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    ) {
                        TopBar(
                            navController = navController,
                            title = "Data Pembelian",
                            color = Color.Transparent
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    ) {
                        Text(
                            text = "Nama Pembelian",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface,
                            fontSize = 10.sp
                        )
                    }
                    ElevationTextField(
                        pembelian.value,
                        { pembelian.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    ) {
                        Text(
                            text = "Harga Pembelian",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface,
                            fontSize = 10.sp
                        )
                    }
                    Row(
                        Modifier
                            .padding(start = 18.dp)
                    ) {
                        Surface(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(5.dp),
                            color = MaterialTheme.colors.background

                        ) {
                            Text(
                                text = "Rp",
                                style = MaterialTheme.typography.body2,
                                color = Color(0xFF6B6B6B),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(
                                        start = 22.dp,
                                        end = 22.dp,
                                        top = 18.dp,
                                        bottom = 18.dp
                                    )
                            )
                        }
                        ElevationTextField(
                            string = harga.value,
                            mutable = {
                                val stripped = it.text.filter { it.isDigit() || it == '.' }
                                val parsed = stripped.toDoubleOrNull()
                                if (parsed != null) {
                                    harga.value  = it.copy(
                                        text = moneyFormatter().format(parsed),
                                        selection = TextRange(moneyFormatter().format(parsed).length)
                                    )
                                } else {
                                    harga.value  = it.copy(
                                        ""
                                    )
                                }
                            },
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    ) {
                        Text(
                            text = "Deskripsi Produk",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface,
                            fontSize = 10.sp
                        )
                    }
                    ElevationTextField(
                        string = deskripsi.value,
                        mutable = { deskripsi.value = it },
                        imeAction = ImeAction.Default,
                        minHeight = 4,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    ) {
                        Button(
                            onClick = {

                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary,
                                contentColor = MaterialTheme.colors.onSurface
                            ),
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Simpan",
                                style = MaterialTheme.typography.h3,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(top = 4.dp, bottom = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}




