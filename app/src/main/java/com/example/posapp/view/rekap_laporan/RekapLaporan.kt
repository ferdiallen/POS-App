package com.example.posapp.view.rekap_laporan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.widgets.general.DatePicker
import com.example.posapp.widgets.general.TopBar

@Composable
fun RekapLaporan(
    navController: NavController
) {

    val context = LocalContext.current
    val tanggalAwal = remember {
        mutableStateOf("")
    }
    val showTanggalAwal = remember {
        mutableStateOf(false)
    }

    val tanggalAkhir = remember {
        mutableStateOf("")
    }

    val showTanggalAkhir = remember {
        mutableStateOf(false)
    }


    Column {
        Box(
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, bottom = 8.dp, top = 8.dp)
        ) {
            TopBar(
                navController = navController,
                title = "Rekap Laporan",
                color = Color.Transparent
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, bottom = 8.dp, top = 8.dp)
        ) {
            DatePicker(
                dateValue = tanggalAwal,
                showDialog = showTanggalAwal,
                context = context,
                text = "Pilih Tanggal Awal"
            ) {

            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, bottom = 8.dp, top = 8.dp)
        ) {
            DatePicker(
                dateValue = tanggalAkhir,
                showDialog = showTanggalAkhir,
                context = context,
                text = "Pilih Tanggal Akhir"
            ) {

            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier
            .padding(start = 18.dp,end = 18.dp)) {
            Button(onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Text(text = "Cetak Laporan",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier
            .padding(start = 18.dp,end = 18.dp)) {
            Button(onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Text(text = "Ganti Password",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}
