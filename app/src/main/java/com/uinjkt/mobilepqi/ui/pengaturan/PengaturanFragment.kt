package com.uinjkt.mobilepqi.ui.pengaturan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uinjkt.mobilepqi.databinding.FragmentPengaturanBinding

class PengaturanFragment : Fragment() {

    private var _binding: FragmentPengaturanBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentPengaturanBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initListener() {
        binding.btnBahasa.setOnClickListener {
            ProfilPengaturanBahasaActivity.start(requireContext())
        }

        with(binding) {
            icAbout.setOnClickListener {
                Toast.makeText(requireContext(), "In Development", Toast.LENGTH_LONG).show()
            }

            icBantuan.setOnClickListener {
                Toast.makeText(requireContext(), "In Development", Toast.LENGTH_LONG).show()
            }

            tvAbout.setOnClickListener {
                Toast.makeText(requireContext(), "In Development", Toast.LENGTH_LONG).show()
            }

            tvBantuan.setOnClickListener {
                Toast.makeText(requireContext(), "In Development", Toast.LENGTH_LONG).show()
            }
        }
    }
}