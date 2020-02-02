package com.sunflower.location

import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Location(val lat: Double,
                    val lon: Double,
                    val cityName: String?,
                    val countryCode: String?)

interface LocationProvider {
    val lastKnownLocation: LiveData<Location?>
}

class CachedLocationProvider(private val geocoder: Geocoder): LocationProvider, LocationListener {

    override val lastKnownLocation: MutableLiveData<Location?> = MutableLiveData()

    override fun onLocationChanged(lat: Double, lon: Double) {
        val addresses: List<Address> = geocoder.getFromLocation(lat, lon, 1)
        val city = addresses.first().adminArea
        val countryCode = addresses.first().countryCode
        lastKnownLocation.value = Location(lat, lon, city, countryCode)
    }
}
