package com.sunflower.current_weather

import com.sunflower.current_weather.api.CurrentWeatherApi
import com.sunflower.current_weather.api.toCurrentWeatherReport

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(location: String): CurrentWeatherReport
    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherReport
}

class ApiCurrentWeatherRepository: CurrentWeatherRepository {
    private val api = CurrentWeatherApi()

    override suspend fun getCurrentWeather(location: String): CurrentWeatherReport {
        return api.getCurrentWeatherAsync(location).toCurrentWeatherReport()
    }

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherReport {
        return api.getCurrentWeatherAsync(lat, lon).toCurrentWeatherReport()
    }
}