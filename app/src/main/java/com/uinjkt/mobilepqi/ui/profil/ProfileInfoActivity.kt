package com.uinjkt.mobilepqi.ui.profil

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilInformasiBinding
import com.uinjkt.mobilepqi.util.Constant
import com.uinjkt.mobilepqi.util.openGallery
import com.uinjkt.mobilepqi.util.uriToFile
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar as JavaCalendar


class ProfileInfoActivity : BaseActivity<ActivityProfilInformasiBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context): Intent {
            return Intent(context, ProfileInfoActivity::class.java)
        }
    }

    private val viewModel by viewModel<ProfilViewModel>()
    private var urlAvatar = ""

    override fun getViewBinding(): ActivityProfilInformasiBinding =
        ActivityProfilInformasiBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.N)
    val myCalendar: Calendar = Calendar.getInstance()
    private val myCalendarJava: JavaCalendar = JavaCalendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
        viewModel.profil()

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    @SuppressLint("CheckResult")
    private fun initListener() {
        binding.icEditImgProfil.setOnClickListener {
            openGallery(launcherIntentGallery)
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
            if (!isPasswordValid && binding.etPassword.text.toString().isNotEmpty()) {
                binding.etPassword.error =
                    "Password harus mengandung minimal 6 karakter yang terdiri dari 1 huruf besar, 1 huruf kecil, 1 angka, dan 1 karakter spesial"
                binding.btnSimpanProfil.isEnabled = isPasswordValid
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.setError(null, null)
                binding.etPassword.clearFocus()
                binding.btnSimpanProfil.isEnabled = true
                binding.btnSimpanProfil.requestFocus()
            } else {
                binding.btnSimpanProfil.isEnabled = isPasswordValid
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

        binding.btnSimpanProfil.setOnClickListener {
            updateProfil()
            binding.etFakultas.isEnabled = false
            binding.etProdi.isEnabled = false
            binding.etPassword.isEnabled = false
            binding.etNohp.isEnabled = false
            binding.etAlamat.isEnabled = false
        }
    }

    private fun updateProfil() {
        viewModel.putprofil(
            PutProfilPayload(
                faculty = binding.etFakultas.text.toString(),
                major = binding.etProdi.text.toString(),
                password = binding.etPassword.text.toString(),
                phone = binding.etNohp.text.toString(),
                address = binding.etAlamat.text.toString(),
                birth = binding.etTglahir.text.toString(),
                avatar = urlAvatar
            )
        )
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initView() {
        val date =
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                if (Build.VERSION.SDK_INT >= 24) {
                    myCalendar[Calendar.YEAR] = year
                    myCalendar[Calendar.MONTH] = month
                    myCalendar[Calendar.DAY_OF_MONTH] = day
                } else {
                    myCalendarJava[JavaCalendar.YEAR] = year
                    myCalendarJava[JavaCalendar.MONTH] = month
                    myCalendarJava[JavaCalendar.DAY_OF_MONTH] = day
                }
                updateLabel()
            }

        binding.icEditTglahir.setOnClickListener {
            with(binding.etTglahir) {
                isEnabled = true
                inputType = 0
                setTextIsSelectable(true)
                isFocusable = false
            }
            val datePicker: DatePickerDialog = if (Build.VERSION.SDK_INT >= 24) {
                DatePickerDialog(
                    this@ProfileInfoActivity, date,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
                )
            } else {
                DatePickerDialog(
                    this@ProfileInfoActivity, date,
                    myCalendarJava[JavaCalendar.YEAR],
                    myCalendarJava[JavaCalendar.MONTH], myCalendarJava[JavaCalendar.DAY_OF_MONTH]
                )
            }
            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
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
    }

    @SuppressLint("CheckResult")
    private fun showData(data: ProfilModel) {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z", Locale.US)
        val newDateFormat = SimpleDateFormat(myFormat, Locale.US)
        if (Build.VERSION.SDK_INT >= 24) {
            dateFormat.format(myCalendar.time)
            newDateFormat.format(myCalendar.time)
        } else {
            dateFormat.format(myCalendarJava.time)
            newDateFormat.format(myCalendarJava.time)
        }
        with(binding) {
            Glide.with(this@ProfileInfoActivity)
                .load(data.avatar)
                .placeholder(R.drawable.img_user)
                .into(imgProfil)

            tvNama.text = data.name
            tvEmail.text = data.email
            tvNomorInduk.text = data.nim
            etFakultas.setText(data.faculty)
            etProdi.setText(data.major)

            val date = dateFormat.parse(data.birth)
            val newDate = date?.let { newDateFormat.format(it) }
            etTglahir.setText(newDate)

            etNohp.setText(data.phone)
            etAlamat.setText(data.address)
        }
    }

    private fun initObserver() {
        viewModel.profil.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.let { showData(it) }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something went wrong")
                }
            }
        }

        viewModel.uploadImage.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.fileUrl?.let { file ->
                        urlAvatar = file
                        Glide.with(this@ProfileInfoActivity)
                            .load(urlAvatar)
                            .into(binding.imgProfil)
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something went wrong")
                }
            }
        }

        viewModel.putprofil.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    setResult(Activity.RESULT_OK)
                    showOneActionDialogWithInvoke(
                        message = getString(R.string.message_profilInfo),
                        btnMessage = getString(R.string.btnMessage_profilInfo),
                        onButtonClicked = { viewModel.profil }
                    )
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something went wrong")
                }
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "image")
            viewModel.uploadImage(Constant.UPLOAD_KEY.AVATAR, Constant.UPLOAD_TYPE.IMAGE, myFile)
        }
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val editText = binding.etTglahir
        if (Build.VERSION.SDK_INT >= 24) {
            editText.setText(dateFormat.format(myCalendar.time))
        } else {
            editText.setText(dateFormat.format(myCalendarJava.time))
        }
    }

    private fun passwordValidate(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,}$"
        return password.matches(passwordPattern.toRegex())
    }
}