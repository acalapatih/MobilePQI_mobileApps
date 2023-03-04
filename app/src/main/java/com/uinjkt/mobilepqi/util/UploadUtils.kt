package com.uinjkt.mobilepqi.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

private const val FILENAME_FORMAT = "dd-MMM-yyyy"

val timeStamp: String = SimpleDateFormat(
    FILENAME_FORMAT, Locale.US
).format(System.currentTimeMillis())

fun createCustomTempImageFile(context: Context): File {
    return File.createTempFile(timeStamp, ".jpeg", context.externalCacheDir)
}

fun createCustomTempPdfFile(context: Context): File {
    //val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
    return File.createTempFile(timeStamp, ".pdf", context.externalCacheDir)
}

fun createCustomTempFile(context: Context, fileName: String): File {
    val fileFormat = fileName.substringAfterLast('.', "")
    return File.createTempFile(timeStamp, ".$fileFormat", context.externalCacheDir)
}

fun uriToFile(uri: Uri, context: Context, type: String): File {
    val contentResolver: ContentResolver = context.contentResolver
    val myFile: File = when (type) {
        "file" -> {
            val fileName = DocumentFile.fromSingleUri(context, uri)?.name!!
            createCustomTempFile(context, fileName)
        }
        "pdf" -> {
            createCustomTempPdfFile(context)
        }
        else -> {
            createCustomTempImageFile(context)
        }
    }

    val inputStream = contentResolver.openInputStream(uri) as InputStream
    val outputStream: OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}