package com.uinjkt.mobilepqi.common

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

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

}