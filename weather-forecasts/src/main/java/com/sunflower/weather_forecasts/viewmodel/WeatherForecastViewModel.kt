package com.sunflower.weather_forecasts.viewmodel

import android.location.Location
import android.location.LocationProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.sunflower.weather_forecasts.repository.WeatherForecastRepository
import com.sunflower.weather_forecasts.view.WeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherForecastViewModel(private val repository: WeatherForecastRepository,
                               private val locationProvider: LocationProvider) : ViewModel() {
/*
    val forecasts: LiveData<List<WeatherForecast>> = liveData {
        val result = repository.getWeatherForecast("Stockholm")
        emit(result)
    }
    
 */
    var lastKnownLocation: Location? = null
    var currentWeather: MutableLiveData<List<WeatherForecast>> = MutableLiveData()
    
    init {
        updateLocation()
    }
    
    private fun updateLocation() {
        locationProvider
    }

    fun getCurrentWeatherBySearch(location: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val report = repository.getWeatherForecast(location)
            currentWeather.value = report
        }
    }

    /*
fun getCurrentWeatherByLocation() {
    viewModelScope.launch(Dispatchers.Main) {
        updateLocation()
        lastKnownLocation?.let {
            val report = repository.getWeatherForecast(it.lat, it.lon)
        }
    }
}
*/
}
