package com.uinjkt.mobilepqi.ui.pengaturan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uinjkt.mobilepqi.databinding.FragmentPengaturanBinding

class PengaturanFragment : Fragment() {

    private var _binding: FragmentPengaturanBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.btnBahasa.setOnClickListener {
//            ProfilePengaturanBahasaActivity.start(this)
//        }
//
//        binding.icBack.setOnClickListener {
//            onBackPressedDispatcher.onBackPressed()
//        }
//        onBackPressedDispatcher.addCallback(this) {
//            finish()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentPengaturanBinding.inflate(inflater, container, false)
        return binding.root
    }
}