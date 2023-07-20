package com.example.posapp.view.pesan_sukses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.R
import com.example.posapp.utils.RouteApp

@Composable
fun PesananSuksesView() {
    Surface(
        Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(start = 50.dp, end = 50.dp, bottom = 48.dp)
            ) {
                Surface(
                    color = MaterialTheme.colors.background,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 12.dp, bottom = 12.dp)
                    ) {
                        Text(
                            text = "Yeay!!!",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icon_berhasil),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Pesanan Berhasil \n Dibuat !!!",
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            Modifier
                                .fillMaxWidth(),
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Column(
                                Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Total Bayar:",
                                    style = MaterialTheme.typography.h3,
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(3.dp))
                                Divider(color = MaterialTheme.colors.onSurface)
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(text = "Rp. 80.000",
                                    style = MaterialTheme.typography.h3,
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)) {
               
                Column {

                    OutlinedButton(onClick = {
                    },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Transparent,
                        ),
                        border = BorderStroke(1.dp,MaterialTheme.colors.background)
                    ) {
                        Text(text = "Cetak Resi",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 14.sp,
                            modifier = Modifier
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(onClick = {
                    },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.background
                        ),
                        elevation = ButtonDefaults.elevation(6.dp)
                    ) {
                        Text(text = "Buat Pesanan",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.primary,
                            fontSize = 14.sp,
                            modifier = Modifier
                        )
                    }

                }
                
            }
        }
    }
}