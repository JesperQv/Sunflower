package com.example.sunflower.data.new_data_classes_test

data class CurrentWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<X>,
    val message: Int
)
