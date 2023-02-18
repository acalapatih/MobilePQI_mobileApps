package com.uinjkt.mobilepqi.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.MainActivity
import com.uinjkt.mobilepqi.data.DataSourceTugasDashboard
import com.uinjkt.mobilepqi.data.DataTugasDashboard
import com.uinjkt.mobilepqi.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var listTugasDashboard: MutableList<DataTugasDashboard>
    private lateinit var tugasDashboardAdapter: DashboardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.imgUser.setOnClickListener {
            MainActivity.start(requireContext(), "profil")
        }

        listTugasDashboard = DataSourceTugasDashboard().dataTugasDashboard

        tugasDashboardAdapter = DashboardAdapter(requireContext(), listTugasDashboard)
        binding.rvTugasDashboard.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTugasDashboard.adapter = tugasDashboardAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
}