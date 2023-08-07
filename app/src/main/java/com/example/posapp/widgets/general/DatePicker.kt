package com.example.posapp.widgets.general

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.R
import java.util.*


@Composable
fun DatePicker(
    dateValue: MutableState<String>,
    showDialog: MutableState<Boolean>,
    context: Context,
    text:String = "Pilih Tanggal",
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, years: Int, months: Int, days: Int ->
            dateValue.value = "$days/${months + 1}/$years"
        }, year, month, day
    )

    if (showDialog.value) {
        datePickerDialog.show()
        showDialog.value = false
    }

    datePickerDialog.setOnDismissListener {
        showDialog.value = false
        datePickerDialog.hide()
        onDismiss.invoke()
    }

    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.surface,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 6.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    showDialog.value = true
                }
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface (
                    color = MaterialTheme.colors.background,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colors.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 5.dp)
                    ) {
                        Text(
                            text = dateValue.value,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface,
                            fontSize = 12.sp
                        )
                    }

                }
                Image(
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                )
            }
        }
    }

}