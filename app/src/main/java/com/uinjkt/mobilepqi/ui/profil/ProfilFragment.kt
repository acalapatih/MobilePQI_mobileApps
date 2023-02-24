package com.uinjkt.mobilepqi.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvProfil.setOnClickListener {
            ProfileInfoActivity.start(requireContext())
        }

        binding.tvPengaturan.setOnClickListener {
            DashboardActivity.start(requireContext(), "pengaturan")
        }

        val baseActivity = activity as DashboardActivity
        binding.tvLogout.setOnClickListener {
            baseActivity.showTwoActionDialog(
                message = getString(R.string.message_logout),
                btnPositiveMessage = getString(R.string.btnPositive_logout),
                btnNegativeMessage = getString(R.string.btnNegative_logout),
                onPositiveButtonClicked = {}
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }
}