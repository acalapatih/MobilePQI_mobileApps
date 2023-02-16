package com.uinjkt.mobilepqi.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uinjkt.mobilepqi.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val close = binding.icClose
//        close.setOnClickListener {view ->
//            DashboardFragment.start(this)
//        }

//        binding.tvProfil.setOnClickListener { view ->
//            ProfileInfoActivity.start(this)
//        }
//
//        binding.tvPengaturan.setOnClickListener { view ->
//            PengaturanFragment.start(this)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }
}