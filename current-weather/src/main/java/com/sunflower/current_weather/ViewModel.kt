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
    var currentWeather: MutableLiveData<CurrentWeatherReport> = MutableLiveData()


    fun getCurrentWeatherByLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            val location = locationProvider.getLastKnownLocation()
            val report = repository.getCurrentWeather(location.lat, location.lon)
            currentWeather.value = report
        }
    }

    fun getCurrentWeatherBySearch(location: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val report = repository.getCurrentWeather(location)
            currentWeather.value = report
        }
    }
}