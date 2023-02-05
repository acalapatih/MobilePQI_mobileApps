package com.uinjkt.mobilepqi

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mobilepqi.core.util.LocationService
import com.uinjkt.mobilepqi.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), LocationService.GetLocationService {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationService: LocationService
    private val locationPermissionCode = 99
    private var isGranted = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationService = LocationService(this)

        requestAccess()
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
                binding.tvLocation.text = it.locality
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
            binding.tvLocation.text = "-"
        }
        alertDialog.show()
    }

    override fun gpsOn() {
        Toast.makeText(this, "Enable", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                requestAccess()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                isGranted = false
            }
        }
    }
}
