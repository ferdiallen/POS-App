package com.example.posapp.view.register

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posapp.R
import com.example.posapp.utils.NavRoute
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.OutlineTextField

@Composable
fun RegisterView(
    navController:NavController
) {

    val state = rememberScrollState()

    val username = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }

    val visibilityIcon by animateIntAsState(targetValue = if (showPassword.value) R.drawable.visibility_off else R.drawable.visibility)

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(18.dp)
                    .verticalScroll(state)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Create an account",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                OutlineTextField(mutableState = username ,
                    keyboarType =  KeyboardType.Text ,
                    label = "Enter Your Username") {

                }
                Spacer(modifier = Modifier.height(28.dp))
                OutlineTextField(mutableState = email ,
                    keyboarType =  KeyboardType.Text ,
                    label = "Enter Your Email") {

                }
                Spacer(modifier = Modifier.height(28.dp))
                OutlineTextField(mutableState = name ,
                    keyboarType =  KeyboardType.Text ,
                    label = "Enter Your Name") {

                }
                Spacer(modifier = Modifier.height(28.dp))
                OutlineTextField(
                    password,
                    KeyboardType.Password,
                    if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    label = "Enter Your Password"
                ) {
                    IconButton(onClick = {
                        showPassword.value = !showPassword.value
                    }) {
                        Icon(
                            painter = painterResource(id = visibilityIcon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(28.dp))
                Button(
                    onClick = {
                        navController.navigate(RouteApp.Login.route) {
                            popUpTo(0)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onSurface
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Sign Up",
                        style = MaterialTheme.typography.h3,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 4.dp))
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Already have an account ?",
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.surface,
                        fontSize = 14.sp)
                    Text(text = " Login",
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(RouteApp.Login.route) {
                                    popUpTo(0)
                                }
                            })
                }
            }
        }
    }
}