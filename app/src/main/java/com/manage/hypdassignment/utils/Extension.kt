package com.manage.hypdassignment.utils

import java.text.DecimalFormat


fun Int.getCurrencyFormat(currency: String):String{
    val formatter = DecimalFormat("#,###,###")
    val formattedAmount: String = formatter.format(this)
    return when(currency){
        "inr" -> "₹$formattedAmount"
        else -> ""
    }

}