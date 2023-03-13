package com.uinjkt.mobilepqi.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.convertTime(newPattern: String): String {
    val output = SimpleDateFormat(newPattern, Locale("id", "ID"))
    var outputString = this
    try {
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id", "ID")).parse(this)
        outputString = input?.let{ output.format(it) }.toString()
    } catch (pe: ParseException) {
        Log.e("ParseException", pe.message.toString())
    }
    return outputString
}