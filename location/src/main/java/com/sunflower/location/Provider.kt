package com.sunflower.location

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first

data class Location(val lat: Double,
                    val lon: Double)

interface LocationProvider {
    suspend fun getLastKnownLocation(): Location
}

@FlowPreview
@ExperimentalCoroutinesApi
internal class CachedLocationProvider: LocationProvider, LocationListener {

    private val channel = ConflatedBroadcastChannel<Location>()

    override fun onLocationChanged(lat: Double, lon: Double) {
        GlobalScope.launch(Dispatchers.Main) {
            channel.send(Location(lat, lon))
        }
    }

    override suspend fun getLastKnownLocation(): Location {
        return channel.asFlow().first()
    }
}
