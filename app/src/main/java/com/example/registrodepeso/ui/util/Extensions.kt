package com.example.registrodepeso.ui.util

import java.util.*

fun Long?.fromUTC(): Date {
    val calendar = GregorianCalendar.getInstance(Locale.getDefault())
    val date = calendar.apply {
        timeInMillis = this@fromUTC ?: calendar.timeInMillis
    }.time
    return Date(date.time -calendar.timeZone.getOffset(Date().time))
}