package com.example.sunflower.weather_list

data class WeatherReport(
    val location: String,
    val weather: String,
    val temp: String,
    val time: String,
    val icon: String
)
