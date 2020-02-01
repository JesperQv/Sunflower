package com.example.weather_forecasts

import com.example.weather_forecasts.weather_list.WeatherForecast

interface WeatherForecastNavigator {
    fun onForecastClicked(forecast: WeatherForecast)
}