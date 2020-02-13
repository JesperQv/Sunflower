package com.sunflower.current_weather.api

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import com.sunflower.common.toWeatherDrawableInt
import com.sunflower.current_weather.CurrentWeatherReport
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("DefaultLocale")
fun CurrentWeatherResponse.toCurrentWeatherReport(): CurrentWeatherReport {
    return CurrentWeatherReport(
        "${this.main.temp} °C",
        "${this.main.temp_min} °C",
        "${this.main.temp_max} °C",
        this.weather.first().main,
        this.weather.first().description.capitalize(),
        this.weather.first().icon.toWeatherDrawableInt(),
        "${this.main.humidity}%",
        "${this.wind.speed} m/s",
        this.name,
        epochTo24H(this.sys.sunrise),
        epochTo24H(this.sys.sunset)
    )
}

fun epochTo24H(epoch: Int): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date(epoch * 1000L))
}

data class CurrentWeatherResponse(
    @SerializedName("coord") val coord: Coord,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)

data class Clouds(@SerializedName("all") val all: Int)

data class Coord(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double
)

data class Sys(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: Double,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)

data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
)
