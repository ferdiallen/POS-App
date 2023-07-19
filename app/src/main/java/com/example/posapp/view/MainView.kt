package com.example.posapp.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.posapp.view.navbar.BotNavigation
import com.example.posapp.view.navigation.NavigationAdapter
import com.example.posapp.R
import com.example.posapp.utils.RouteApp

@Composable
fun MainView() {

    val navController = rememberNavController()

    val cart = remember {
        mutableStateOf(0)
    }
    val showFloat = remember {
        mutableStateOf(false)
    }

    val showBottomBar = remember {
        mutableStateOf(true)
    }

    showFloat.value = cart.value > 0

    Scaffold(
        bottomBar = {
            if (showBottomBar.value) {
                Surface(
                    color = Color.Transparent,
                    elevation = 18.dp,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ) {
                    BotNavigation(navController = navController)
                }
            }
        },
        backgroundColor = Color.Transparent,
        floatingActionButton = {
            AnimatedVisibility(visible = showFloat.value) {
                FloatingActCart(cart) {
                    cart.value = 0
                    navController.navigate(RouteApp.Pesanan.route)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {

    Surface(
        Modifier
            .padding(it)
            .fillMaxSize()
        ,
        color = Color.Transparent) {
            NavigationAdapter(navController = navController,showBottomBar) {
                cart.value = cart.value + 1
                Log.d("ShowValue ",showFloat.value.toString())
            }

    }



    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FloatingActCart(cart: MutableState<Int>,onClick:() -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)


    ) {
        Surface(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            onClick = {
                onClick.invoke()
            }
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${cart.value} Items",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = "Total: Rp 50.000",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp
                )
            }
        }
    }
}
