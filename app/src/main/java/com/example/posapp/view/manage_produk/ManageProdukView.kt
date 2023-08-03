package com.example.posapp.view.manage_produk

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.data.ProductEntity
import com.example.posapp.utils.CategoryProduct
import com.example.posapp.utils.RouteApp
import com.example.posapp.utils.moneyFormatter
import com.example.posapp.widgets.general.ElevationTextField
import com.example.posapp.widgets.general.TopBar
import com.example.posapp.widgets.manager_produk.PickPhotoByGalery
import java.text.DecimalFormat

private val dropdownMenuList = listOf(
    CategoryProduct.MAKANAN, CategoryProduct.MINUMAN
)

@Composable
fun ManageProdukView(
    navController: NavController
) {
    val viewModel: AddProductViewModel = hiltViewModel()
    val context = LocalContext.current
    val namaProduk = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val hargaProduk = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val deskripsiProduk = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val kategoriProduk = remember {
        mutableStateOf("")
    }
    val imageByUri = remember {
        mutableStateOf<Uri?>(null)
    }
    var shouldShowDropdownMenu by remember {
        mutableStateOf(false)
    }
    val formatter = DecimalFormat("#,###")
    Box {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier
                        .padding(start = 18.dp, end = 18.dp, bottom = 18.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.saveProduct(
                                ProductEntity(
                                    namaProduk = namaProduk.value.text,
                                    harga = hargaProduk.value.text.replace(",", "")
                                        .toInt(),
                                    deskripsi = deskripsiProduk.value.text,
                                    kategori = kategoriProduk.value,
                                    fotoProduk = viewModel.saveImageToStorage(
                                        context = context,
                                        imageByUri.value ?: return@Button
                                    )
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_rounded),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Simpan",
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
                            title = "Manage Produk",
                            color = MaterialTheme.colors.surface
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        Modifier
                            .padding(start = 18.dp, end = 18.dp)
                            .fillMaxWidth(),
                        color = MaterialTheme.colors.background,
                        elevation = 6.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .padding(start = 12.dp, end = 12.dp)
                            ) {
                                Text(
                                    text = "Details Produk",
                                    style = MaterialTheme.typography.h3,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 14.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .padding(start = 12.dp, end = 12.dp)
                            ) {
                                Text(
                                    text = "Nama Produk",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp
                                )
                            }
                            ElevationTextField(
                                string = namaProduk.value,
                                mutable = {
                                    namaProduk.value = it
                                },
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
                                        fontSize = 10.sp,
                                        modifier = Modifier
                                            .padding(
                                                start = 24.dp,
                                                end = 24.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    )
                                }
                                ElevationTextField(
                                    string = hargaProduk.value,
                                    mutable = {
                                        val stripped = it.text.filter { it.isDigit() || it == '.' }
                                        val parsed = stripped.toDoubleOrNull()
                                        if (parsed != null) {
                                            hargaProduk.value = it.copy(
                                                text = moneyFormatter().format(parsed),
                                                selection = TextRange(moneyFormatter().format(parsed).length)
                                            )
                                        } else {
                                            hargaProduk.value = it.copy(
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
                                string = deskripsiProduk.value,
                                mutable = { deskripsiProduk.value = it },
                                imeAction = ImeAction.Default,
                                minHeight = 4,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .padding(start = 18.dp, end = 18.dp)
                            ) {
                                Text(
                                    text = "Kategori Produk",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                ElevationTextField(
                                    string = TextFieldValue(text = kategoriProduk.value),
                                    mutable = {

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {

                                        }, trailingIcon = {
                                        IconButton(onClick = {
                                            shouldShowDropdownMenu = !shouldShowDropdownMenu
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.KeyboardArrowDown,
                                                contentDescription = "",
                                                tint = Color.Black
                                            )
                                        }
                                    }
                                )
                                DropdownMenu(expanded = shouldShowDropdownMenu,
                                    onDismissRequest = {
                                        shouldShowDropdownMenu = false
                                    },modifier = Modifier.fillMaxWidth()) {
                                    dropdownMenuList.forEach {
                                        DropdownMenuItem(onClick = {
                                            kategoriProduk.value = it.name
                                            shouldShowDropdownMenu = false
                                        }) {
                                            Text(text = it.name)
                                        }
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(10.dp))
//                            KATEGORI PRODUK
                            Box(
                                modifier = Modifier
                                    .padding(start = 18.dp, end = 18.dp)
                            ) {
                                Text(
                                    text = "Foto Produk",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.surface,
                                    fontSize = 10.sp
                                )
                            }
                            PickPhotoByGalery(uriImage = imageByUri)
                        }
                    }
                }
            }
        }
    }
}
