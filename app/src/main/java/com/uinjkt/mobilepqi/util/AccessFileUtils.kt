package com.uinjkt.mobilepqi.util

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

fun openGallery(launcher: ActivityResultLauncher<Intent>) {
    val intent = Intent()
    intent.action = Intent.ACTION_GET_CONTENT
    intent.type = "image/*"
    val chooser = Intent.createChooser(intent, "Choose a Picture")
    launcher.launch(chooser)
}

fun openFileManager(launcher: ActivityResultLauncher<Intent>) {
    val intent = Intent()
    intent.action = Intent.ACTION_GET_CONTENT
    intent.type = "*/*"
    val chooser = Intent.createChooser(intent, "Choose a File")
    launcher.launch(chooser)
}

fun openFileManagerPdf(launcher: ActivityResultLauncher<Intent>) {
    val intent = Intent()
    intent.action = Intent.ACTION_GET_CONTENT
    intent.type = "application/pdf"
    val chooser = Intent.createChooser(intent, "Choose a PDF File")
    launcher.launch(chooser)
}