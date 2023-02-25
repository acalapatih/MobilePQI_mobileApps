package com.uinjkt.mobilepqi.ui.dashboard.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.uinjkt.mobilepqi.data.DataSourceTugasDashboard
import com.uinjkt.mobilepqi.data.DataTugasDashboard
import com.uinjkt.mobilepqi.databinding.FragmentDashboardBinding
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import com.uinjkt.mobilepqi.ui.dashboard.adapter.DashboardAdapter
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardSharedViewModel
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardViewModel
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.jadwalSholat.observe(viewLifecycleOwner) { model ->
            when (model) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    model.data?.let { setupJadwalSholat(it) }
                }
                is Resource.Error -> {
                    model.message?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        binding.tvWaktu.text = "-"
                    }
                }
            }
        }

        sharedViewModel.longitude.observe(viewLifecycleOwner) { value ->
            longitude = value.toString()
            if (latitude.isNotEmpty() && longitude.isNotEmpty()) {
                viewModel.getJadwalSholat(latitude, longitude)
            }
        }

        sharedViewModel.latitude.observe(viewLifecycleOwner) { value ->
            latitude = value.toString()
            if (latitude.isNotEmpty() && longitude.isNotEmpty()) {
                viewModel.getJadwalSholat(latitude, longitude)
            }
        }

        sharedViewModel.location.observe(viewLifecycleOwner) { value ->
            if (value.isNotEmpty()) {
                binding.tvDaerah.text = value
            } else {
                binding.tvDaerah.text = "-"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupJadwalSholat(data: JadwalSholatModel) {
        binding.tvWaktu.text = "${data.subuh} WIB"
    }

    private fun initView() {
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
}