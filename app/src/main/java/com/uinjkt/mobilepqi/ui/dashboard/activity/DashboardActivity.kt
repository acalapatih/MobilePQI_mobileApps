package com.uinjkt.mobilepqi.ui.dashboard.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobilepqi.core.util.LocationService
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMainBinding
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardSharedViewModel
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardActivity : BaseActivity<ActivityMainBinding>(), LocationService.GetLocationService {
    companion object {
        @JvmStatic
        fun start(context: Context, value: String? = "", classId: Int? = 0) {
            val starter = Intent(context, DashboardActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("action", value)
                .putExtra("class_id", classId)
            context.startActivity(starter)
        }
    }

    private val classId by lazy { intent.getIntExtra("class_id", 0) }
    private val sharedViewModel by viewModel<DashboardSharedViewModel>()
    private lateinit var locationService: LocationService
    private val locationPermissionCode = 99
    private var doubleBackToExitPressedOnce = false
    private var isGranted = true

    private lateinit var navController: NavController
    lateinit var navView: BottomNavigationView

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationService = LocationService(this)
        requestAccess()

        initBottomNav()
        handleOnBackPressed()
    }

    private fun handleOnBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            val tag = navController.currentDestination?.id

            if (tag != null) {
                if (tag == R.id.navigation_dashboard) {
                    if (doubleBackToExitPressedOnce) {
                        finish()
                        return@addCallback
                    }
                    doubleBackToExitPressedOnce = true
                    showToast("Press Back Again to Exit")
                    Handler(Looper.getMainLooper()).postDelayed(
                        { doubleBackToExitPressedOnce = false },
                        2000
                    )
                } else {
                    navView.selectedItemId = R.id.navigation_dashboard
                }
            }
        }
    }

    private fun initBottomNav() {
        navView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)

        /*
        https://stackoverflow.com/a/54613997
         */

        when (intent.getStringExtra("action")) {
            "profil" -> {
                navController.navigate(R.id.navigation_profil)
            }
            "pengaturan" -> {
                navController.navigate(R.id.navigation_pengaturan)
            }
            else -> {
                val bundle = bundleOf("class_id" to classId)
                navController.navigate(R.id.navigation_dashboard, bundle)
            }
        }
    }

    private fun requestAccess() {
        if ((ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        } else {
            locationService.getLocation(this)
        }
    }

    private fun showLocation(latitude: Double, longitude: Double) {
        Geocoder(this, Locale.getDefault()).getAddress(
            latitude, longitude
        ) {
            if (it != null) {
                sharedViewModel.location.value = it.subLocality ?: ""
                sharedViewModel.latitude.value = latitude
                sharedViewModel.longitude.value = longitude
            }
        }
    }

    private fun Geocoder.getAddress(
        latitude: Double,
        longitude: Double,
        address: (Address?) -> Unit
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getFromLocation(latitude, longitude, 1) { address(it.firstOrNull()) }
            return
        }

        try {
            address(getFromLocation(latitude, longitude, 1)?.firstOrNull())
        } catch (e: Exception) {
            //will catch if there is an internet problem
            address(null)
        }
    }

    private fun showAlert() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("SETTINGS")
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?")
        alertDialog.setPositiveButton("Settings") { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
            setDefaultValueSharedViewModel()
        }
        alertDialog.show()
    }

    private fun setDefaultValueSharedViewModel() {
        sharedViewModel.latitude.value = 0.0
        sharedViewModel.longitude.value = 0.0
        sharedViewModel.location.value = "-"
    }

    override fun gpsOn() {
        showToast("GPS Enable")
    }

    override fun gpsOff() {
        showAlert()
    }

    override fun locationDetected(location: Location) {
        showLocation(location.latitude, location.longitude)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("Access Location Permission Granted")
                requestAccess()
            } else {
                showToast("Access Location Permission Denied")
                isGranted = false
                setDefaultValueSharedViewModel()
            }
        }
    }
}
