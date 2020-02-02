package com.sunflower.location

interface LocationListener {
    fun onLocationChanged(lat: Double, lon: Double)
}
