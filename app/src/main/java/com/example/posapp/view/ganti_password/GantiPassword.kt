package com.example.posapp.view.ganti_password

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.ElevationTextField
import com.example.posapp.widgets.general.TopBar

@Composable
fun GantiPassword(
    navController: NavController
) {

    val passwordSaatIni = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val showPasswordSaatIni = remember {
        mutableStateOf(false)
    }

    val passwordBaru = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val showPasswordBaru = remember {
        mutableStateOf(false)
    }

    val konfirmasiPasswordBaru = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val showKonfirmasiPasswordBaru = remember {
        mutableStateOf(false)
    }

    val visibilityIcon by animateIntAsState(targetValue = if (showPasswordSaatIni.value) R.drawable.visibility_off else R.drawable.visibility)


    Box {
        Scaffold (
            backgroundColor = MaterialTheme.colors.background
                ) {
            Surface(
                Modifier
                    .padding(it)
                    .fillMaxSize(),
            color = MaterialTheme.colors.background) {
               Column {
                   Box(modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp)) {
                       TopBar(navController = navController ,
                           title = "Ganti Password" ,
                           color = Color.Transparent )
                   }
                   Spacer(modifier = Modifier.height(20.dp))
                   Box(modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp)) {
                       Text("Masukkan password saat ini",
                           style = MaterialTheme.typography.body1,
                           color = MaterialTheme.colors.surface,
                           fontSize = 10.sp)
                   }
                   Spacer(modifier = Modifier.height(10.dp))
                   Box(modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp)) {
                       Text("Password Saat Ini",
                           style = MaterialTheme.typography.body2,
                           color = MaterialTheme.colors.surface,
                           fontSize = 10.sp)
                   }
                   ElevationTextField(string = passwordSaatIni.value ,
                       mutable = {
                           passwordSaatIni.value = it
                       },
                       keyboardType = KeyboardType.Password,
                       visualTransformation = if (showPasswordSaatIni.value) VisualTransformation.None else PasswordVisualTransformation(),
                       trailingIcon = {
                           IconButton(onClick = { showPasswordSaatIni.value = !showPasswordSaatIni.value }) {
                               Icon(painter = painterResource(id = visibilityIcon),
                                   contentDescription = null,
                                   tint = MaterialTheme.colors.surface)

                           }
                       },
                       modifier = Modifier
                           .fillMaxWidth())
                   Spacer(modifier = Modifier.height(15.dp))
                   Box(modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp)) {
                       Text("Masukan password baru",
                           style = MaterialTheme.typography.body1,
                           color = MaterialTheme.colors.surface,
                           fontSize = 10.sp)
                   }
                   Spacer(modifier = Modifier.height(15.dp))
                   Box(modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp)) {
                       Text("Password Baru",
                           style = MaterialTheme.typography.body2,
                           color = MaterialTheme.colors.surface,
                           fontSize = 10.sp)
                   }
                   ElevationTextField(string = passwordBaru.value ,
                       mutable = {
                           passwordBaru.value = it
                       },
                       keyboardType = KeyboardType.Password,
                       visualTransformation = if (showPasswordBaru.value) VisualTransformation.None else PasswordVisualTransformation(),
                       trailingIcon = {
                           IconButton(onClick = { showPasswordBaru.value = !showPasswordBaru.value }) {
                               Icon(painter = painterResource(id = visibilityIcon),
                                   contentDescription = null,
                                   tint = MaterialTheme.colors.surface)

                           }
                       },
                        modifier = Modifier
                            .fillMaxWidth())
                   Spacer(modifier = Modifier.height(15.dp))
                   Box(modifier = Modifier
                       .padding(start = 18.dp, end = 18.dp)) {
                       Text("Konfirmasi Password Baru",
                           style = MaterialTheme.typography.body2,
                           color = MaterialTheme.colors.surface,
                           fontSize = 10.sp)
                   }
                   ElevationTextField(string = konfirmasiPasswordBaru.value ,
                       mutable = {
                           konfirmasiPasswordBaru.value = it
                       },
                       keyboardType = KeyboardType.Password,
                       visualTransformation = if (showKonfirmasiPasswordBaru.value) VisualTransformation.None else PasswordVisualTransformation(),
                       trailingIcon = {
                           IconButton(onClick = { showKonfirmasiPasswordBaru.value = !showKonfirmasiPasswordBaru.value }) {
                               Icon(painter = painterResource(id = visibilityIcon),
                                   contentDescription = null,
                                   tint = MaterialTheme.colors.surface)

                           }
                       },
                       modifier = Modifier
                           .fillMaxWidth())
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
                           )
                       }
                   }
               }
            }
        }
    }
}