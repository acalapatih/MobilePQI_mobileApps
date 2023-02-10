package com.uinjkt.mobilepqi.common

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogOneActionBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogOneActionThinFontBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogTwoActionBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract fun getViewBinding(): VB

    private lateinit var _binding: VB
    val binding: VB
        get() {
            if (::_binding.isInitialized) return _binding
            else _binding = getViewBinding()
            return _binding
        }

    private fun getLayoutResource(): View = binding.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(getLayoutResource())
    }

    protected fun showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }

    protected fun showOneActionDialog(message: String, btnMessage: String) {
        val dialog = Dialog(this)
        val dialogBinding = ItemDialogOneActionBinding.inflate(layoutInflater)
        val window = dialog.window

        window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)

        val deviceWidth: Int = Resources.getSystem().displayMetrics.widthPixels
        val margin = (60 * Resources.getSystem().displayMetrics.density).toInt()
        val width: Int = deviceWidth - margin
        window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        with(dialogBinding) {
            tvMessage.text = message
            btnOkay.text = btnMessage
            btnOkay.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    protected fun showOneActionThinFontDialog(message: String, btnMessage: String) {
        val dialog = Dialog(this)
        val dialogBinding = ItemDialogOneActionThinFontBinding.inflate(layoutInflater)
        val window = dialog.window

        window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)

        val deviceWidth: Int = Resources.getSystem().displayMetrics.widthPixels
        val margin = (60 * Resources.getSystem().displayMetrics.density).toInt()
        val width: Int = deviceWidth - margin
        window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        with(dialogBinding) {
            tvMessage.text = message
            btnOkay.text = btnMessage
            btnOkay.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    protected fun showTwoActionDialog(
        message: String,
        bodyMessage: String = "",
        isBodyMessageVisible: Boolean = false,
        btnPositiveMessage: String,
        btnNegativeMessage: String,
        onPositiveButtonClicked: () -> Unit
    ) {
        val dialog = Dialog(this)
        val dialogBinding = ItemDialogTwoActionBinding.inflate(layoutInflater)
        val window = dialog.window

        window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)

        val deviceWidth: Int = Resources.getSystem().displayMetrics.widthPixels
        val margin = (60 * Resources.getSystem().displayMetrics.density).toInt()
        val width: Int = deviceWidth - margin
        window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        with(dialogBinding) {
            tvMessage.text = message
            tvBodyMessage.isVisible = isBodyMessageVisible
            tvBodyMessage.text = bodyMessage
            btnYes.text = btnPositiveMessage
            btnNo.text = btnNegativeMessage
            btnYes.setOnClickListener {
                onPositiveButtonClicked.invoke()
                dialog.dismiss()
            }
            btnNo.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}