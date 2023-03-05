package com.uinjkt.mobilepqi.util

fun String.getFileNameFromUrl(): String {
    return this.substring(this.lastIndexOf('/') + 1)
}

fun String.getFileExtension(): String {
    return this.substringAfterLast('.', "")
}