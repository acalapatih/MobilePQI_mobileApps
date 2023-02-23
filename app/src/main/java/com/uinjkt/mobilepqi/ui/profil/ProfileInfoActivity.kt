package com.uinjkt.mobilepqi.ui.profil

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import com.jakewharton.rxbinding2.widget.RxTextView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilInformasiBinding
import io.reactivex.Observable
import java.util.*


class ProfileInfoActivity : BaseActivity<ActivityProfilInformasiBinding>() {
    /**
     * Called when the activity is first created.
     */

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfileInfoActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityProfilInformasiBinding =
        ActivityProfilInformasiBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.N)
    val myCalendar: Calendar = Calendar.getInstance()

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        val date =
            OnDateSetListener { _, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }

        binding.icEditTglahir.setOnClickListener {
            with(binding.etTglahir) {
                isEnabled = true
                inputType = 0
                setTextIsSelectable(true)
                isFocusable = false
            }
            val datePicker = DatePickerDialog(
                this@ProfileInfoActivity, date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
            )
            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
        }

        binding.btnSimpanProfil.setOnClickListener {
            val tgLahir = binding.etTglahir.text.toString()
            Log.v("tgLahir", tgLahir)
        }

        binding.icEditFakultas.setOnClickListener {
            binding.etFakultas.isEnabled = true
        }

        binding.icEditProdi.setOnClickListener {
            binding.etProdi.isEnabled = true
        }

        binding.icEditPassword.setOnClickListener {
            binding.etPassword.isEnabled = true
        }

        binding.icEditNohp.setOnClickListener {
            binding.etNohp.isEnabled = true
        }

        binding.icEditAlamat.setOnClickListener {
            binding.etAlamat.isEnabled = true
        }

        val fakultasStream = RxTextView.textChanges(binding.etFakultas)
            .skipInitialValue()
            .map { fakultas ->
                fakultas.isNotEmpty()
            }
        fakultasStream.subscribe { isFakultasValid ->
            if (!isFakultasValid) {
                binding.etFakultas.setError("Harap masukkan Fakultas Anda dengan benar!", null)
            }
        }

        val prodiStream = RxTextView.textChanges(binding.etProdi)
            .skipInitialValue()
            .map { prodi ->
                prodi.isNotEmpty()
            }
        prodiStream.subscribe { isProdiValid ->
            if (!isProdiValid) {
                binding.etProdi.setError("Harap masukkan Prodi Anda dengan benar!", null)
            }
        }

        val passwordStream = RxTextView.textChanges(binding.etPassword)
            .skipInitialValue()
            .map { password ->
                passwordValidate(password.toString())
            }
        passwordStream.subscribe { isPasswordValid ->
            if (!isPasswordValid) {
                binding.etPassword.setError(
                    "Password harus mengandung minimal 6 karakter yang terdiri dari 1 huruf besar, 1 huruf kecil, 1 angka, dan 1 karakter spesial",
                    null
                )
            }
        }

        val tglahirStream = RxTextView.textChanges(binding.etTglahir)
            .skipInitialValue()
            .map { tglahir ->
                tglahir.isNotEmpty()
            }
        tglahirStream.subscribe { isTglahirValid ->
            if (!isTglahirValid) {
                binding.etTglahir.setError("Harap masukkan Tanggal Lahir Anda dengan benar!", null)
            }
        }

        val nohpStream = RxTextView.textChanges(binding.etNohp)
            .skipInitialValue()
            .map { tglahir ->
                tglahir.isNotEmpty()
            }
        nohpStream.subscribe { isNohpValid ->
            if (!isNohpValid) {
                binding.etNohp.setError("Harap masukkan No Hp Anda dengan benar!", null)
            }
        }

        val alamatStream = RxTextView.textChanges(binding.etAlamat)
            .skipInitialValue()
            .map { tglahir ->
                tglahir.isNotEmpty()
            }
        alamatStream.subscribe { isAlamatValid ->
            if (!isAlamatValid) {
                binding.etAlamat.setError("Harap masukkan Alamat Anda dengan benar!", null)
            }
        }

        Observable.combineLatest(
            fakultasStream,
            prodiStream,
            passwordStream,
            tglahirStream,
            nohpStream,
            alamatStream
        ) { fakultasValid: Boolean, prodiValid: Boolean, passwordValid: Boolean,
            tglahirValid: Boolean, nohpValid: Boolean, alamatValid: Boolean ->
            fakultasValid && prodiValid && passwordValid && tglahirValid && nohpValid && alamatValid
        }.subscribe { isButtonValid ->
            binding.btnSimpanProfil.isEnabled = isButtonValid
        }

        binding.btnSimpanProfil.setOnClickListener {
            showOneActionDialog(
                message = getString(R.string.message_profilInfo),
                btnMessage = getString(R.string.btnMessage_profilInfo)
            )
            binding.etFakultas.isEnabled = false
            binding.etProdi.isEnabled = false
            binding.etPassword.isEnabled = false
            binding.etNohp.isEnabled = false
            binding.etAlamat.isEnabled = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val editText = binding.etTglahir
        editText.setText(dateFormat.format(myCalendar.time))
    }

    private fun passwordValidate(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,}$"
        return password.matches(passwordPattern.toRegex())
    }


}