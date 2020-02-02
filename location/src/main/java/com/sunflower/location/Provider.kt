package com.sunflower.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Location(val lat: Double,
                    val lon: Double)

interface LocationProvider {
    val lastKnownLocation: LiveData<Location?>
}

class CachedLocationProvider: LocationProvider, LocationListener {

    override val lastKnownLocation: MutableLiveData<Location?> = MutableLiveData()

    override fun onLocationChanged(lat: Double, lon: Double) {
        lastKnownLocation.value = Location(lat, lon)
    }
}
