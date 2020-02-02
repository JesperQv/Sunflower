package com.sunflower.weather_forecasts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sunflower.weather_forecasts.repository.WeatherForecastRepository
import com.sunflower.weather_forecasts.view.WeatherForecast

class WeatherForecastViewModel(private val repository: WeatherForecastRepository) : ViewModel() {

    val forecasts: LiveData<List<WeatherForecast>> = liveData {
        val result = repository.getWeatherForecast("Stockholm")
        emit(result)
    }
}
