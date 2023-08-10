package com.example.posapp.widgets.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.posapp.R
import com.example.posapp.data.CheckoutModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ItemOrder(
    item: CheckoutModel,
    index: Int,
    value: MutableState<Int>
) {
    val numberFormat = remember{
        NumberFormat.getCurrencyInstance(Locale("in","ID")).format(item.price)
    }
    Surface(
        Modifier
            .fillMaxWidth(),
        elevation = 6.dp,
        color = MaterialTheme.colors.background
    ) {
        Row(
            Modifier
                .padding(
                    start = 3.dp,
                    end = 6.dp,
                    top = 3.dp,
                    bottom = 3.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp
                    )
                    Text(
                        text = numberFormat,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 14.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.icon_b_minus),
                            contentDescription = null,
                            modifier = Modifier
                                .size(21.dp)
                                .clickable {
                                    if (value.value > 0) {
                                        value.value -= 1
                                    }
                                })
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colors.surface.copy(0.3f),
                            elevation = 0.dp,
                            modifier = Modifier
                                .size(17.dp)

                        ) {
                            Text(
                                text = value.value.toString(),
                                style = MaterialTheme.typography.body2,
                                color = Color(0xFF979797),
                                fontSize = 8.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(1.dp))
                        Image(painter = painterResource(id = R.drawable.icon_b_plus),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable {

                                    value.value += 1

                                })
                    }
                }
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.deleteicon),
                    contentDescription = null,
                    tint = Color(0xFF3F3F3F)
                )
            }
        }
    }
}