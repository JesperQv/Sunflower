package com.sunflower.weather_forecasts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunflower.location.LocationProvider
import com.sunflower.weather_forecasts.repository.WeatherForecastRepository
import com.sunflower.weather_forecasts.view.WeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val repository: WeatherForecastRepository,
    private val locationProvider: LocationProvider
) : ViewModel() {
    var forecasts: MutableLiveData<List<WeatherForecast>> = MutableLiveData()

    fun getWeatherForecastByLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            val location = locationProvider.getLastKnownLocation()
            val report = repository.getWeatherForecast(location.lat, location.lon)
            forecasts.value = report
        }
    }

    fun getWeatherForecastBySearch(location: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val report = repository.getWeatherForecast(location)
            forecasts.value = report
        }
    }
}
