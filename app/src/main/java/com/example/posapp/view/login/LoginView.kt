package com.example.posapp.view.login

import android.content.IntentSender
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
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
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = {
            if (it.resultCode == ComponentActivity.RESULT_OK) {
                viewModel.signInResult(it.data ?: return@rememberLauncherForActivityResult)
            }
        }
    )
    val loginState = viewModel.loginState
    LaunchedEffect(key1 = loginState, block = {
        if (loginState != null) {
            navController.navigate(NavRoute.Home.route){
                popUpTo(RouteApp.Login.route){
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
                            val res = viewModel.signInWithGoogle()
                            result.launch(
                                IntentSenderRequest.Builder(
                                    res ?: return@launch
                                ).build()
                            )
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

