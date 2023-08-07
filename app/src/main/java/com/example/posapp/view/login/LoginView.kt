package com.example.posapp.view.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.posapp.MainActivity
import com.example.posapp.R
import com.example.posapp.utils.NavRoute
import com.example.posapp.utils.RouteApp
import com.example.posapp.widgets.general.CustomCheckBox
import com.example.posapp.widgets.general.OutlineTextField
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    navController: NavController
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val result = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            viewModel.signInResult(it.data ?: return@rememberLauncherForActivityResult)
        }
    )
    val loginState = viewModel.loginState
    LaunchedEffect(key1 = loginState, block = {
        if (loginState != null) {
            navController.navigate(NavRoute.Home.route) {
                popUpTo(RouteApp.Login.route) {
                    inclusive = true
                }
            }
        }
    })
    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val showPassword = remember {
        mutableStateOf(false)
    }

    val isChecked = remember {
        mutableStateOf(false)
    }

    val state = rememberScrollState()

    val visibilityIcon by animateIntAsState(targetValue = if (showPassword.value) R.drawable.visibility_off else R.drawable.visibility)

    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(MaterialTheme.colors.background)
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            elevation = 0.dp
        ) {
            Column(
                Modifier
                    .verticalScroll(state)
                    .padding(18.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally)
                ) {
                    Text(
                        text = "Hi, Welcome",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.surface,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.surface,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlineTextField(
                    email,
                    KeyboardType.Text,
                    label = "example@gmail.com"
                ) {

                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.surface,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
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
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        CustomCheckBox(isChecked = isChecked)
//                        Checkbox(checked = isChecked.value ,
//                            onCheckedChange = {isChecked.value = it},
//                            colors = CheckboxDefaults.colors(
//                                uncheckedColor = Color(0xFFCDD1E0),
//                                checkedColor = Color(0xFFCDD1E0),
//                            ))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Remember Me",
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.surface,
                            fontSize = 13.sp
                        )
                    }
                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.body1,
                        color = Color(0xFFE86969),
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        navController.navigate(NavRoute.Home.route) {
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
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.h3,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.signInWithGoogle(onDataResult = {
                                result.launch(
                                    it
                                )
                            })
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(R.raw.google)
                                .decoderFactory(SvgDecoder.Factory()).build(),
                            contentDescription = "", modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Sign in with Google", color = Color.White)
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally)
                ) {
                    Text(
                        text = "Don't have an account ?",
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.surface,
                        fontSize = 14.sp
                    )
                    Text(text = " Sign Up",
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(RouteApp.Register.route)
                            })
                }
            }
        }
    }
}

