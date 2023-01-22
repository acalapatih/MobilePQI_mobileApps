package com.mobilepqi.core.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationService : LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var context: Context
    private val locationPermissionCode = 2


    fun initLocationManager(context: Context) {
        this.context = context
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    fun getLocation(provider: String, activity: Activity): Location? {
        if (locationManager.isProviderEnabled(provider)) {
            if ((ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED)
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    locationPermissionCode
                )
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
                return locationManager.getLastKnownLocation(provider)
            }
        }
        return null
    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText(context, "Enable GPS", Toast.LENGTH_SHORT).show()
    }

    override fun onProviderDisabled(provider: String) {

    }

    override fun onLocationChanged(location: Location) {

    }

}