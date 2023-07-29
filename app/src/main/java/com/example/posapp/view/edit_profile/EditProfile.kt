package com.example.posapp.view.edit_profile

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.widgets.edit_profile.CustomForm
import com.example.posapp.widgets.general.FormTelphone
import com.example.posapp.widgets.general.TopBar
import com.example.posapp.widgets.manager_produk.PickRoundedPhoto

@Composable
fun EditProfileView(
    navController: NavController
) {
    
    val getUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val namaToko = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val nama = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val email = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val handPhone = remember {
        mutableStateOf(TextFieldValue(""))
    }
    
    Box (
        Modifier
            .padding(bottom = 16.dp)
            ) {
        Surface(
            Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)) {
                    TopBar(navController = navController ,
                        title = "Edit Profile" ,
                        color = Color.Transparent )
                }
                Spacer(modifier = Modifier.height(30.dp))
                PickRoundedPhoto(uriImage = getUri)
                Spacer(modifier = Modifier.height(20.dp))
                CustomForm(mutableString = namaToko , desc = "Nama Toko" )
                Spacer(modifier = Modifier.height(15.dp))
                CustomForm(mutableString = nama , desc = "Nama" )
                Spacer(modifier = Modifier.height(15.dp))
                CustomForm(mutableString = email , desc = "Email" )
                Spacer(modifier = Modifier.height(15.dp))
                FormTelphone(nomor = handPhone)
            }
            
        }

        Box(modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Bottom)
            .padding(start = 18.dp,end = 18.dp)) {
            Button(onClick = { },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Text(text = "Simpan",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }
        }
    }
}

