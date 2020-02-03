package com.sunflower.location

data class Location(val lat: Double,
                    val lon: Double)

interface LocationProvider {
    var lastKnownLocation: Location?
}

internal class CachedLocationProvider: LocationProvider, LocationListener {

    override var lastKnownLocation: Location? = null

    override fun onLocationChanged(lat: Double, lon: Double) {
        lastKnownLocation = Location(lat, lon)
    }
}
