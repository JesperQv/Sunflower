package com.sunflower.weather_forecasts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunflower.weather_forecasts.data.WeatherApi
import com.sunflower.weather_forecasts.data.toWeatherForecastList
import com.sunflower.weather_forecasts.weather_list.WeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherForecastViewModel() : ViewModel() {
    private val api = WeatherApi()
    val forecasts = MutableLiveData<List<WeatherForecast>>()

    init {
        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = api.getCurrentWeatherAsync("Stockholm,SE").await()
            forecasts.value = currentWeatherResponse.toWeatherForecastList()
        }
    }
}
