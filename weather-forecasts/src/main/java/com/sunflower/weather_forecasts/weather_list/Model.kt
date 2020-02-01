package com.sunflower.weather_forecasts.weather_list

data class WeatherForecast(
    val location: String,
    val weather: String,
    val temp: String,
    val time: String,
    val icon: String
)
