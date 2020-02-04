package com.sunflower.weather_forecasts.viewmodel


import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunflower.location.LocationProvider
import com.sunflower.weather_forecasts.repository.WeatherForecastRepository
import com.sunflower.weather_forecasts.view.WeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherForecastViewModel(private val repository: WeatherForecastRepository,
                              private val locationProvider: LocationProvider
): ViewModel() {
    private var lastKnownLocation: com.sunflower.location.Location? = null
    var forecasts: MutableLiveData<List<WeatherForecast>> = MutableLiveData()

    init {
        updateLocation()
    }

    private fun updateLocation() {
        locationProvider.lastKnownLocation?.let { lastKnownLocation = it }
    }

    fun getCurrentWeatherBySearch(location: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val report = repository.getWeatherForecast(location)
            forecasts.value = report
        }
    }
}
