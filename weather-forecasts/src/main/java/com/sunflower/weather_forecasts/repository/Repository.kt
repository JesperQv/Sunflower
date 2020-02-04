package com.sunflower.weather_forecasts.repository

import com.sunflower.common.normalize
import com.sunflower.weather_forecasts.api.WeatherApi
import com.sunflower.weather_forecasts.api.toWeatherForecastList
import com.sunflower.weather_forecasts.view.WeatherForecast

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(location: String): List<WeatherForecast>
    suspend fun getWeatherForecast(lat: Double, lon: Double): List<WeatherForecast>
}

class CachedWeatherForecastRepository : WeatherForecastRepository {
    private val api = WeatherApi()

    override suspend fun getWeatherForecast(location: String): List<WeatherForecast> {
        return api.getCurrentWeatherAsync(normalize(location)).toWeatherForecastList()
    }

    override suspend fun getWeatherForecast(lat: Double, lon: Double): List<WeatherForecast> {
        return api.getCurrentWeatherAsync(lat, lon).toWeatherForecastList()
    }
}
