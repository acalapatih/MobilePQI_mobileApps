package com.uinjkt.mobilepqi.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.FragmentProfilBinding
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ProfilViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.profil.observe(viewLifecycleOwner) { model ->
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
                    Toast.makeText(requireContext(), "Something when wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showData(data: ProfilModel) {
        with(binding) {
            Glide.with(this@ProfilFragment)
                .load(data.avatar)
                .into(imgUser)
            tvNama.text = data.name
            tvNomorInduk.text = data.nim
        }
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    private fun initListener() {
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

    private fun initView() {
        viewModel.profil()
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