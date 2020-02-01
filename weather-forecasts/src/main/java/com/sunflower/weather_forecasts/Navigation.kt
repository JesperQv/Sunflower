package com.sunflower.weather_forecasts

import com.sunflower.weather_forecasts.weather_list.WeatherForecast

interface WeatherForecastNavigator {
    fun onForecastClicked(forecast: WeatherForecast)
}