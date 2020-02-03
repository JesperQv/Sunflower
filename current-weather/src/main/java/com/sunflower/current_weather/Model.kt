package com.sunflower.current_weather

data class CurrentWeatherReport(val temp: Double,
                                val minTemp: Double,
                                val maxTemp: Double,
                                val weather: String,
                                val weatherDescription: String,
                                val iconId: String,
                                val humidity: Int,
                                val windSpeed: Double,
                                val locationName: String,
                                val sunrise: Int,
                                val sunset: Int)