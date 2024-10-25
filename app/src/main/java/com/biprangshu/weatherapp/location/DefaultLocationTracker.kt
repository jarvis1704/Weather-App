package com.biprangshu.weatherapp.location

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

import com.biprangshu.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient

import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker{
    @RequiresApi(Build.VERSION_CODES.P)
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermisson= ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )== PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermisson= ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )== PackageManager.PERMISSION_GRANTED
        val locationManager=application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!hasAccessCoarseLocationPermisson || !hasAccessFineLocationPermisson || !isGpsEnabled){
            return null
        }

        return suspendCancellableCoroutine { cont->
            locationClient.lastLocation.apply {
                if(isComplete){
                    if(isSuccessful){
                        cont.resume(result)
                    } else{
                        cont.resume(value = null)
                    }
                    addOnSuccessListener {
                        cont.resume(it)
                    }
                    addOnFailureListener {
                        cont.resume(null)
                    }
                    addOnCanceledListener{
                        cont.cancel()
                    }
                }
            }

        }

    }
}