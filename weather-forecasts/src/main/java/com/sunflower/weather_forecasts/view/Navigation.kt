package com.sunflower.weather_forecasts.view

import com.sunflower.weather_forecasts.view.WeatherForecast

interface WeatherForecastNavigator {
    fun onForecastClicked(forecast: WeatherForecast)
}