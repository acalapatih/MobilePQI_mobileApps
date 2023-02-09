package com.mobilepqi.core.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationService(private val listener: GetLocationService) : LocationListener {

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    fun getLocation(activity: Activity) {
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                activity,
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
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onProviderEnabled(provider: String) {
        listener.gpsOn()
    }

    override fun onProviderDisabled(provider: String) {
        listener.gpsOff()
    }

    override fun onLocationChanged(location: Location) {
        listener.locationDetected(location)
    }

    interface GetLocationService {
        fun gpsOn()
        fun gpsOff()
        fun locationDetected(location: Location)
    }

}