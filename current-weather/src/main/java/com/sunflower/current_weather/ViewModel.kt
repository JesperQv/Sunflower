package com.sunflower.current_weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunflower.location.Location
import com.sunflower.location.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(private val repository: CurrentWeatherRepository,
                              private val locationProvider: LocationProvider): ViewModel() {
    private var lastKnownLocation: Location? = null
    var currentWeather: MutableLiveData<CurrentWeatherReport> = MutableLiveData()

    init {
        updateLocation()
    }

    private fun updateLocation() {
        locationProvider.lastKnownLocation?.let { lastKnownLocation = it }
    }

    fun getCurrentWeatherByLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            updateLocation()
            lastKnownLocation?.let {
                val report = repository.getCurrentWeather(it.lat, it.lon)
                currentWeather.value = report
            }
        }
    }

    fun getCurrentWeatherBySearch(location: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val report = repository.getCurrentWeather(location)
            currentWeather.value = report
        }
    }
}