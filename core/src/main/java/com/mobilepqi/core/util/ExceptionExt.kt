package com.mobilepqi.core.util

import org.json.JSONObject
import retrofit2.HttpException

fun Throwable.setGeneralError(): String {
    val exception = (this as? HttpException)?.response()?.errorBody()?.string() ?: ""
    return "${this.message} - ${getErrorMessage(exception)}"
}

private fun getErrorMessage(raw: String): String {
    val obj = JSONObject(raw)
    val obj2 = JSONObject(obj.getString("data"))
    return obj2.getString("errors")
}