package com.manage.hypdassignment.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
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
val boldTextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
val largeBoldTextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
val bigBoldTextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
val bigMediumTextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
val mediumTextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
val mediumBigTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)