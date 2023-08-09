package com.example.posapp.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.posapp.R
import com.example.posapp.utils.NavRoute
import com.example.posapp.utils.RouteApp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Profile(
    navController: NavController
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state = rememberScrollState()

    val userInformation = viewModel.currentUserData

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
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
                    .verticalScroll(state)
                    .padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 8.dp,
                    color = MaterialTheme.colors.background
                ) {
                    Row(
                        Modifier
                            .padding(top = 16.dp, bottom = 16.dp, start = 18.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            modifier = Modifier
                                .size(40.dp)
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.profile_male),
//                                contentDescription = null
//                            )
                            AsyncImage(
                                model = userInformation?.profilePicture,
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Hi,",
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.surface,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "${userInformation?.username}",
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.surface,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "${userInformation?.email}",
                                style = MaterialTheme.typography.body2,
                                color = Color(0xFF797979),
                                fontSize = 10.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 8.dp,
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier
                            .padding(top = 4.dp, bottom = 4.dp, start = 18.dp, end = 16.dp)
                    ) {
                        Surface(
                            onClick = {
                                navController.navigate(RouteApp.SetAktif.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.set_aktif),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Set Kasir Aktif",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 8.dp,
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier
                            .padding(top = 16.dp, bottom = 16.dp, start = 18.dp, end = 16.dp)
                    ) {
                        Surface(
                            onClick = {
                                navController.navigate(RouteApp.EditProfile.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.edit_profile),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Edit Profile",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            onClick = {
                                navController.navigate(RouteApp.GantiPassword.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ganti_password),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Ganti Password",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Surface(
                            onClick = {
                                navController.navigate(RouteApp.AddMenu.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.manajemen_icon),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Manajemen Produk",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            onClick = {
                                navController.navigate(RouteApp.DataPenjualan.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.penjualan_icon),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Data Penjualan",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            onClick = {
                                navController.navigate(RouteApp.DataPembelian.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.pembelian_icon),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Data Pembelian",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Surface(
                            onClick = {
                                      navController.navigate(RouteApp.RekapLaporan.route)
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.rekap_laporan),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Rekap Laporan",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 8.dp,
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier
                            .padding(top = 4.dp, bottom = 4.dp, start = 18.dp, end = 16.dp)
                    ) {
                        Surface(
                            onClick = {
                                viewModel.signOutUser {
                                    navController.navigate(RouteApp.Login.route) {
                                        popUpTo(NavRoute.Home.route){
                                            inclusive = true
                                        }
                                    }
                                }
                            },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.logout),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Keluar",
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colors.surface
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth(CenterHorizontally)
                ) {
                    Text(
                        text = "Aplikasi Versi 1.0.0",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.surface.copy(0.4f),
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}