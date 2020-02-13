package com.sunflower.current_weather

data class CurrentWeatherReport(
    val temp: String,
    val minTemp: String,
    val maxTemp: String,
    val weather: String,
    val weatherDescription: String,
    val icon: Int,
    val humidity: String,
    val windSpeed: String,
    val locationName: String,
    val sunrise: String,
    val sunset: String
)
