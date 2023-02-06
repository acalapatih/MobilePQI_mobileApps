package com.uinjkt.mobilepqi.ui.signin

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.uinjkt.mobilepqi.R


class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        // show hide password with eye icon
        var isSelected = true
        val iv_show_hide_password: ImageView = findViewById(R.id.iv_show_hide_password)
        val txt_password: EditText = findViewById(R.id.txt_password_signin)
        iv_show_hide_password.setOnClickListener {
            if (isSelected) {
                iv_show_hide_password.setImageResource(R.drawable.ic_eye_hide_password)
                val start = txt_password.getSelectionStart()
                val end = txt_password.getSelectionEnd()
                txt_password.setTransformationMethod(null)
                txt_password.setSelection(start, end)
                isSelected = false;
            } else {
                iv_show_hide_password.setImageResource(R.drawable.ic_eye_show_password)
                val start = txt_password.getSelectionStart()
                val end = txt_password.getSelectionEnd()
                txt_password.transformationMethod = PasswordTransformationMethod()
                txt_password.setSelection(start, end)
                isSelected = true;
            }
        }
    }
}