package com.sunflower.weather_forecasts.api

import com.sunflower.weather_forecasts.view.WeatherForecast
import com.google.gson.annotations.SerializedName

fun CurrentWeatherResponse.toWeatherForecastList(): List<WeatherForecast> {
    val location = this.city.name

    return this.list.map { val weather = it.weather.first().main
        val temp = it.main.temp
        val time = it.dtTxt
        val icon = it.weather.first().icon
        WeatherForecast(location, weather, "$temp°C", time, icon) }
}

data class CurrentWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<X>,
    val message: Int
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class Clouds(
    val all: Int
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    val humidity: Int,
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

data class Rain(
    @SerializedName("3h")
    val h: Double
)

data class Snow(
    @SerializedName("3h")
    val h: Double
)

data class Sys(
    val pod: String
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val speed: Double
)

data class X(
    val clouds: Clouds,
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val rain: Rain,
    val snow: Snow,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)
