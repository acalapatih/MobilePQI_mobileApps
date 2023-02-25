package com.uinjkt.mobilepqi.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataSourceTugasDashboard
import com.uinjkt.mobilepqi.data.DataTugasDashboard
import com.uinjkt.mobilepqi.databinding.FragmentDashboardBinding
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import com.uinjkt.mobilepqi.ui.dashboard.adapter.DashboardAdapter
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardSharedViewModel
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardViewModel
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DashboardViewModel>()
    private val sharedViewModel by activityViewModel<DashboardSharedViewModel>()

    private lateinit var listTugasDashboard: MutableList<DataTugasDashboard>
    private lateinit var tugasDashboardAdapter: DashboardAdapter
    private var latitude = ""
    private var longitude = ""
    private var currentTimestamp = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()

        if (latitude.isEmpty() && longitude.isEmpty()) {
            binding.tvSholat.text = "-"
            binding.tvWaktu.text = "-"
        }
    }

    private fun initObserver() {
        viewModel.jadwalSholat.observe(viewLifecycleOwner) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.let { setupJadwalSholat(it) }
                }
                is Resource.Error -> {
                    showLoading(false)
                    model.message?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        binding.tvWaktu.text = "-"
                    }
                }
            }
        }

        sharedViewModel.longitude.observe(viewLifecycleOwner) { value ->
            longitude = value.toString()
            if (latitude.isNotEmpty() && longitude.isNotEmpty() && latitude != "0.0" && longitude != "0.0") {
                viewModel.getJadwalSholat(currentTimestamp, latitude, longitude)
            }
        }

        sharedViewModel.latitude.observe(viewLifecycleOwner) { value ->
            latitude = value.toString()
            if (latitude.isNotEmpty() && longitude.isNotEmpty() && latitude != "0.0" && longitude != "0.0") {
                viewModel.getJadwalSholat(currentTimestamp, latitude, longitude)
            }
        }

        sharedViewModel.location.observe(viewLifecycleOwner) { value ->
            if (value.isNotEmpty()) {
                binding.tvDaerah.text = value
            } else {
                binding.tvDaerah.text = "-"
                binding.tvSholat.text = "-"
            }
        }
    }

    private fun setupJadwalSholat(data: JadwalSholatModel) {
        val currentTimestamp = System.currentTimeMillis()
        val sdf = SimpleDateFormat(FORMAT_DATE, Locale.getDefault())
        val currentDate = sdf.format(Date())

        val jadwalSubuh = SimpleDateFormat(
            FORMAT_DATE_TIME,
            Locale.getDefault()
        ).parse("$currentDate ${data.subuh}")?.time ?: 0
        val jadwalZuhur = SimpleDateFormat(
            FORMAT_DATE_TIME,
            Locale.getDefault()
        ).parse("$currentDate ${data.zuhur}")?.time ?: 0
        val jadwalAsar = SimpleDateFormat(
            FORMAT_DATE_TIME,
            Locale.getDefault()
        ).parse("$currentDate ${data.ashar}")?.time ?: 0
        val jadwalMaghrib = SimpleDateFormat(
            FORMAT_DATE_TIME,
            Locale.getDefault()
        ).parse("$currentDate ${data.maghrib}")?.time ?: 0
        val jadwalIsya = SimpleDateFormat(
            FORMAT_DATE_TIME,
            Locale.getDefault()
        ).parse("$currentDate ${data.isya}")?.time ?: 0

        if (currentTimestamp < jadwalSubuh) {
            binding.tvSholat.text = getString(R.string.sholat_subuh)
            binding.tvWaktu.text = String.format(getString(R.string.waktu_sholat), data.subuh)
        } else if (currentTimestamp in (jadwalSubuh + 1) until jadwalZuhur) {
            binding.tvSholat.text = getString(R.string.sholat_zuhur)
            binding.tvWaktu.text = String.format(getString(R.string.waktu_sholat), data.zuhur)
        } else if (currentTimestamp in (jadwalZuhur + 1) until jadwalAsar) {
            binding.tvSholat.text = getString(R.string.sholat_ashar)
            binding.tvWaktu.text = String.format(getString(R.string.waktu_sholat), data.ashar)
        } else if (currentTimestamp in (jadwalAsar + 1) until jadwalMaghrib) {
            binding.tvSholat.text = getString(R.string.sholat_maghrib)
            binding.tvWaktu.text = String.format(getString(R.string.waktu_sholat), data.maghrib)
        } else if (currentTimestamp in (jadwalMaghrib + 1) until jadwalIsya) {
            binding.tvSholat.text = getString(R.string.sholat_isya)
            binding.tvWaktu.text = String.format(getString(R.string.waktu_sholat), data.maghrib)
        } else if (currentTimestamp > jadwalIsya) {
            //val calendar = Calendar.getInstance()
            //calendar.add(Calendar.DAY_OF_YEAR, 1)
            //val tomorrowDate = Timestamp(calendar.time.time).toString()
            binding.tvSholat.text = getString(R.string.sholat_subuh)
            binding.tvWaktu.text = String.format(getString(R.string.waktu_sholat), data.subuh)
        }

    }

    private fun initView() {
        currentTimestamp = Timestamp(System.currentTimeMillis()).toString()
        listTugasDashboard = DataSourceTugasDashboard().dataTugasDashboard

        tugasDashboardAdapter = DashboardAdapter(requireContext(), listTugasDashboard)
        binding.rvTugasDashboard.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTugasDashboard.adapter = tugasDashboardAdapter
    }

    private fun initListener() {
        binding.imgUser.setOnClickListener {
            DashboardActivity.start(requireContext(), "profil")
        }
    }

    private fun showLoading(state: Boolean) {
        with(binding) {
            if (state) {
                tvSholatLoading.isVisible = true
                tvSholat.isVisible = false
                tvWaktu.isVisible = false
                tvSholatLoading.startShimmer()
            } else {
                tvSholat.isVisible = true
                tvWaktu.isVisible = true
                tvSholatLoading.isVisible = false
                tvSholatLoading.stopShimmer()
            }
        }
    }

    companion object {
        private const val FORMAT_DATE = "dd/MM/yyyy"
        private const val FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm"
    }
}