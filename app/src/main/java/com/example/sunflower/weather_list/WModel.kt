package com.example.sunflower.weather_list

data class WeatherReport(val location: String,
                         val weather: String,
                         val temp: Double,
                         val weatherUrl: String)
