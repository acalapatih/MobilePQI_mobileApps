package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.data.source.remote.response.tugas.UpdateDetailTugasPayload
import com.mobilepqi.core.domain.model.common.FileItem
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenBuatTugasBaruBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar as JavaCalendar

class DosenBuatEditTugasActivity : BaseActivity<ActivityDosenBuatTugasBaruBinding>(),
    MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, behavior: String = "buat", idKelas: Int = 0, idTugas: Int = 0) {
            val starter = Intent(context, DosenBuatEditTugasActivity::class.java)
                .putExtra(BEHAVIOR, behavior)
                .putExtra(ID_KELAS, idKelas)
                .putExtra(ID_TUGAS, idTugas)
            context.startActivity(starter)
        }

        private const val BEHAVIOR = "behavior"
        private const val ID_KELAS = "idKelas"
        private const val ID_TUGAS = "idTugas"
    }

    private val behavior by lazy { intent.getStringExtra(BEHAVIOR) }
    private val idKelas by lazy { intent.getIntExtra(ID_KELAS, 0) }
    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }

    @RequiresApi(Build.VERSION_CODES.N)
    private val myCalendar: Calendar = Calendar.getInstance()
    private val myCalendarJava: JavaCalendar = JavaCalendar.getInstance()

    private val viewModel by viewModel<DosenBuatEditTugasViewModel>()
    private lateinit var listFileAttached: MutableList<FileItem>
    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private lateinit var spinnerJenisArrayAdapter: ArrayAdapter<String>
    private lateinit var spinnerTopikArrayAdapter: ArrayAdapter<String>
    private var urlFile = ""
    private val listDataTopik =
        arrayOf("Praktikum Qiroah", "Praktikum Ibadah", "Hafalan Doa", "Hafalan Surah")
    private val listDataJenisTugas = arrayOf("Individu", "Kelompok")


    override fun getViewBinding(): ActivityDosenBuatTugasBaruBinding =
        ActivityDosenBuatTugasBaruBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initView()
        initListener()
        initObserver()

    }

    private fun initView() {
        if (behavior == "buat") {
            binding.tvTitleMenuBuatTugasDosen.text =
                getString(R.string.tv_title_menu_buat_tugas_dosen)
        } else {
            binding.tvTitleMenuBuatTugasDosen.text =
                getString(R.string.tv_title_menu_edit_tugas_dosen, "topik", "judul tugas")
            getDetailTugas()
        }

    }

    private fun getDetailTugas() {
        viewModel.getDetailTugas(idTugas)
    }

    private fun initAdapter() {

        // Topik Tugas Adapter
        spinnerTopikArrayAdapter = ArrayAdapter(this, R.layout.item_spinner, listDataTopik)
        spinnerTopikArrayAdapter.setDropDownViewResource(R.layout.item_dropdown_spinner)
        binding.spinnerTopikTugas.adapter = spinnerTopikArrayAdapter

        // Jenis Tugas Adapter
        spinnerJenisArrayAdapter = ArrayAdapter(this, R.layout.item_spinner, listDataJenisTugas)
        spinnerJenisArrayAdapter.setDropDownViewResource(R.layout.item_dropdown_spinner)
        binding.spinnerJenisTugas.adapter = spinnerJenisArrayAdapter

        // File Recycle View Adapter
        listFileAttached = mutableListOf()
        fileUploadedByDosenAdapter =
            MahasiswaFileUploadedByAdapterList(this, listFileAttached, "delete", this)
        fileUploadedByDosenAdapter.setData(listFileAttached)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)
    }


    private fun initObserver() {
        viewModel.getdetailTugas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        afterGetDetailTugas(it)
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showloading(false)
                }
            }
        }
        viewModel.fileUploaded.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    binding.pbFileLoading.isVisible = true
                }
                is Resource.Success -> {
                    model.data?.fileUrl?.let { file ->
                        urlFile = file
                        if (listFileAttached.isEmpty()) {
                            listFileAttached.addAll(listOf(FileItem(urlFile)))
                        } else {
                            listFileAttached.add(0, FileItem(urlFile))
                        }
                        fileUploadedByDosenAdapter.setData(listFileAttached)
                        if (!isChangingConfigurations) {
                            externalCacheDir?.let { cache -> deleteTempFile(cache) }
                        }
                    }
                    binding.pbFileLoading.isVisible = false
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    binding.pbFileLoading.isVisible = false
                }
            }
        }
        viewModel.createTugas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    showOneActionDialogWithInvoke("Tugas Berhasil Ditambahkan", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showloading(false)
                }
            }
        }
        viewModel.updateDetailTugas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    showOneActionDialogWithInvoke("Tugas Berhasil Diubah", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showloading(false)
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun afterGetDetailTugas(model: GetDetailTugasModel) {
        val breadcrumbs = binding.tvTitleMenuBuatTugasDosen
        val title = model.title
        binding.etDescJudulBuatTugasDosen.setText(title)
        binding.etDescDeskripsiBuatTugasDosen.setText(model.description)
        listFileAttached = model.file.toMutableList()
        fileUploadedByDosenAdapter.setData(listFileAttached)
        when (model.topic) {
            "praktikum qiroah" -> {
                breadcrumbs.text =
                    getString(
                        R.string.tv_title_menu_edit_tugas_dosen,
                        "Praktikum Qiroah",
                        title.capitalizeEachWord()
                    )
                binding.spinnerTopikTugas.setSelection(0)
            }
            "praktikum ibadah" -> {
                breadcrumbs.text =
                    getString(
                        R.string.tv_title_menu_edit_tugas_dosen,
                        "Praktikum Ibadah",
                        title.capitalizeEachWord()
                    )
                binding.spinnerTopikTugas.setSelection(1)
            }
            "hafalan doa" -> {
                breadcrumbs.text =
                    getString(
                        R.string.tv_title_menu_edit_tugas_dosen,
                        "Hafalan Doa",
                        title.capitalizeEachWord()
                    )
                binding.spinnerTopikTugas.setSelection(2)
            }
            "hafalan surat" -> {
                breadcrumbs.text =
                    getString(
                        R.string.tv_title_menu_edit_tugas_dosen,
                        "Hafalan Surah",
                        title.capitalizeEachWord()
                    )
                binding.spinnerTopikTugas.setSelection(3)
            }
        }
        when (model.jenis) {
            "individu" -> binding.spinnerJenisTugas.setSelection(0)
            "kelompok" -> binding.spinnerJenisTugas.setSelection(1)
        }
        binding.tvDeadlineTugas.text = model.deadline.convertTime("dd/MM/yyyy")
    }

    private fun showloading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvBuatTugas.isVisible = !value
    }

    private fun initListener() {
        with(binding) {
            ivAttachFile.setOnClickListener {
                openFileManager(launcherIntentFile)
            }

            ivInsertLink.setOnClickListener {
                // TODO("Masukkan Link")
            }

            ivImageAttach.setOnClickListener {
                openGallery(launcherIntentGallery)
            }

            ivMicAttach.setOnClickListener {
                // TODO("Rekam Suara")
            }

            ivLogoBackCircleButtonBuatTugas.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            tvDeadlineTugas.setOnClickListener {
                with(tvDeadlineTugas) {
                    isEnabled = true
                    inputType = 0
                    setTextIsSelectable(true)
                    isFocusable = false
                }
                openCalendar()
            }

            btnPostingTugasBaruDosen.setOnClickListener {
                if (tvDeadlineTugas.text.toString() == "Deadline") {
                    showOneActionDialog("Tentukan Deadlinenya", "Okay")
                } else if (etDescJudulBuatTugasDosen.text.toString().isEmpty()) {
                    showOneActionDialog("Judul Tidak Boleh Kosong", "Okay")
                } else {
                    when (behavior) {
                        "buat" -> createTugas()
                        "edit" -> updateTugas()
                    }
                }
            }
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun updateTugas() {
        viewModel.updateDetailTugas(
            UpdateDetailTugasPayload(
                title = binding.etDescJudulBuatTugasDosen.text.toString(),
                description = binding.etDescDeskripsiBuatTugasDosen.text.toString(),
                jenis = binding.spinnerJenisTugas.selectedItem.toString().lowercase(),
                topic = when (binding.spinnerTopikTugas.selectedItem.toString()) {
                    "Hafalan Surah" -> "Hafalan Surat"
                    else -> binding.spinnerTopikTugas.selectedItem.toString()
                },
                deadline = binding.tvDeadlineTugas.text.toString(),
                file = listFileAttached.map {
                    UpdateDetailTugasPayload.FileItem(
                        url = it.url
                    )
                }
            ),
            idTugas
        )
    }

    private fun createTugas() {
        val topic = binding.spinnerTopikTugas.selectedItem.toString()
        viewModel.createTugas(
            CreateTugasPayload(
                title = binding.etDescJudulBuatTugasDosen.text.toString(),
                jenis = binding.spinnerJenisTugas.selectedItem.toString(),
                description = binding.etDescDeskripsiBuatTugasDosen.text.toString(),
                topic = when (topic) {
                    "Hafalan Surah" -> "hafalan surat"
                    else -> topic.lowercase()
                },
                file = listFileAttached.map {
                    CreateTugasPayload.FileItem(
                        url = it.url
                    )
                },
                deadline = binding.tvDeadlineTugas.text.toString()
            ), idKelas
        )
    }


    private fun openCalendar() {
        val date = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
            } else {
                myCalendarJava[JavaCalendar.YEAR] = year
                myCalendarJava[JavaCalendar.MONTH] = month
                myCalendarJava[JavaCalendar.DAY_OF_MONTH] = day
            }
            updatelabel()
        }
        val datePicker: DatePickerDialog
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePicker = DatePickerDialog(
                this@DosenBuatEditTugasActivity,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            )
        } else {
            datePicker = DatePickerDialog(
                this@DosenBuatEditTugasActivity,
                date,
                myCalendarJava[JavaCalendar.YEAR],
                myCalendarJava[JavaCalendar.MONTH],
                myCalendarJava[JavaCalendar.DAY_OF_MONTH]
            )
        }
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.show()
    }


    private fun updatelabel() {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvDeadlineTugas.text = dateFormat.format(myCalendar.time)
        } else {
            binding.tvDeadlineTugas.text = dateFormat.format(myCalendarJava.time)
        }
    }

    private val launcherIntentFile = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "file")
            viewModel.uploadFileOrImage(
                Constant.UPLOAD_KEY.TUGAS,
                Constant.UPLOAD_TYPE.FILE,
                myFile
            )
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "image")
            viewModel.uploadFileOrImage(
                Constant.UPLOAD_KEY.TUGAS,
                Constant.UPLOAD_TYPE.IMAGE,
                myFile
            )
        }
    }

    private fun deleteTempFile(file: File): Boolean {
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (f in files) {
                    if (f.isDirectory) {
                        deleteTempFile(f)
                    } else {
                        f.delete()
                    }
                }
            }
        }
        return file.delete()
    }

    override fun onUserClickListener(action: String, position: Int) {
        if (action == "delete") {
            listFileAttached.removeAt(position)
            fileUploadedByDosenAdapter.setData(listFileAttached)
        }
    }
}