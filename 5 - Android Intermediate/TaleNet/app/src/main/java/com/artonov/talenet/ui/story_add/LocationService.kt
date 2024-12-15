package com.artonov.talenet.ui.story_add

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationService(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Function to get current location
    suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            // Check if permissions are granted
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                continuation.resumeWithException(SecurityException("Permission not granted"))
                return@suspendCancellableCoroutine
            }

            // Request the last known location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        continuation.resume(location)
                    } else {
                        continuation.resume(null) // No location available
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }

            // Handle cancellation of the coroutine
            continuation.invokeOnCancellation {
                fusedLocationClient.removeLocationUpdates { }
            }
        }
    }
}