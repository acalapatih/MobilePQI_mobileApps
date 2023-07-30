package com.uinjkt.mobilepqi.util

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import java.io.File

fun downloadFileToStorage(baseActivity: Context, url: String?, title: String?) {
    val direct = File(Environment.getExternalStorageDirectory().toString() + "/MobilePQI")

    if (!direct.exists()) {
        direct.mkdirs()
    }
    val dm = baseActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val uri = Uri.parse(url)
    val request = DownloadManager.Request(uri)
    request.setDestinationInExternalPublicDir(
        Environment.DIRECTORY_DOWNLOADS,
        title
    )
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    request.setTitle(title)
    Toast.makeText(baseActivity, "Downloading a File...", Toast.LENGTH_SHORT).show()

    dm.enqueue(request)
}