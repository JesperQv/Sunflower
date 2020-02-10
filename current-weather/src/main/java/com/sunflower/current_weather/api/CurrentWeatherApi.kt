package com.sunflower.current_weather.api

import com.sunflower.common.buildApiClient
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeatherAsync(
        @Query("q") location: String,
        @Query("units") unitCode: String = "metric"
    ): CurrentWeatherResponse

    @GET("weather")
    suspend fun getCurrentWeatherAsync(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unitCode: String = "metric"
    ): CurrentWeatherResponse

    companion object {
        operator fun invoke(): CurrentWeatherApi = buildApiClient()
    }
}
