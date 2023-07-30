package com.uinjkt.mobilepqi.ui.profil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.FragmentProfilBinding
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import com.uinjkt.mobilepqi.ui.signin.SigninActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ProfilViewModel>()
    private lateinit var baseActivity: DashboardActivity

    private val createClassLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                viewModel.profil()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as DashboardActivity
    }

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
                    model.message?.let {
                        model.message.let { Toast.makeText(requireContext(), it ?: "Something went wrong", Toast.LENGTH_SHORT).show() }
                    }
                }
            }
        }
    }

    private fun showData(data: ProfilModel) {
        with(binding) {
            Glide.with(this@ProfilFragment)
                .load(data.avatar)
                .placeholder(R.drawable.img_user)
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
            createClassLauncher.launch(ProfileInfoActivity.start(requireContext()))
        }

        binding.tvPengaturan.setOnClickListener {
            baseActivity.navView.selectedItemId = R.id.navigation_pengaturan
        }

        val baseActivity = activity as DashboardActivity
        binding.tvLogout.setOnClickListener {
            baseActivity.showTwoActionDialog(
                message = getString(R.string.message_logout),
                btnPositiveMessage = getString(R.string.btnPositive_logout),
                btnNegativeMessage = getString(R.string.btnNegative_logout),
                onPositiveButtonClicked = { logOut() }
            )
        }
    }

    private fun logOut() {
        viewModel.clearAllSharedPreferences()
        val intent = Intent(requireContext(), SigninActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
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