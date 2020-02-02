package com.sunflower.weather_forecasts.repository

import com.sunflower.weather_forecasts.api.WeatherApi
import com.sunflower.weather_forecasts.api.toWeatherForecastList
import com.sunflower.weather_forecasts.view.WeatherForecast

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(location: String): List<WeatherForecast>
}

class CachedWeatherForecastRepository : WeatherForecastRepository {
    private val api = WeatherApi()

    override suspend fun getWeatherForecast(location: String): List<WeatherForecast> {
        val currentWeatherResponse = api.getCurrentWeatherAsync(location).await()
        return currentWeatherResponse.toWeatherForecastList()
    }
}
