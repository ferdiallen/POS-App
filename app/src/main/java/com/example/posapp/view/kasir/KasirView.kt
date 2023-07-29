package com.example.posapp.view.kasir

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.widgets.edit_profile.CustomForm
import com.example.posapp.widgets.general.FormTelphone
import com.example.posapp.widgets.general.TopBar

@Composable
fun KasirView(
    navController: NavController
) {

    val namaKasir = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val handPhone = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Box(
        Modifier
            .padding(bottom = 16.dp)
    ) {
        Surface(
            Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 18.dp)
                ) {
                    TopBar(
                        navController = navController,
                        title = "Edit Profile",
                        color = Color.Transparent
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    Modifier
                        .padding(start = 18.dp, end = 18.dp)
                ) {
                    Text(text = "Masukan Nama Kasir Yang Bertugas saat ini",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 10.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))
                CustomForm(mutableString = namaKasir ,
                    desc = "Nama Kasir" )
                Spacer(modifier = Modifier.height(15.dp))
                FormTelphone( nomor = handPhone )
                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier
                    .padding(start = 18.dp,end = 18.dp)) {
                    Button(onClick = { },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(text = "Set Aktif",
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
}