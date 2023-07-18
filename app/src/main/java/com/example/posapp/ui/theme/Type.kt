package com.example.posapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.posapp.R

// Set of Material typography styles to start with

val poppins = FontFamily(listOf(
    Font(R.font.poppins_bold,
        weight = FontWeight.Bold),
    Font(R.font.poppins_medium,
        weight = FontWeight.Medium),
    Font(R.font.poppins_regular,
        weight = FontWeight.Normal
    ),
    Font(R.font.poppins_semibold,
        weight = FontWeight.SemiBold
    )

))

val rubik = FontFamily(listOf(
    Font(R.font.rubik_medium,
        weight = FontWeight.Medium)

))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal
    ),
    body2 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium
    ),
    h1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
    ),
    h2 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Medium,
    ),
    h3 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)