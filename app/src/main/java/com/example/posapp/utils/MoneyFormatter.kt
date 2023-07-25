package com.example.posapp.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun moneyFormatter(): DecimalFormat {
    val symbols = DecimalFormatSymbols(Locale.US)
    symbols.decimalSeparator = '.'
    symbols.groupingSeparator = ','
    return DecimalFormat("#,###", symbols)
}