package com.sunflower.common

fun String.toWeatherDrawableInt(): Int {
    return when {
        this == "01d" -> R.drawable.ic_01d
        this == "01n" -> R.drawable.ic_01n
        this == "02d" -> R.drawable.ic_02d
        this == "02n" -> R.drawable.ic_02n
        this == "03d" -> R.drawable.ic_03d
        this == "03n" -> R.drawable.ic_03n
        this == "04d" -> R.drawable.ic_04d
        this == "04n" -> R.drawable.ic_04n
        this == "09d" -> R.drawable.ic_09d
        this == "09n" -> R.drawable.ic_09n
        this == "10d" -> R.drawable.ic_10d
        this == "10n" -> R.drawable.ic_10n
        this == "11d" -> R.drawable.ic_11d
        this == "11n" -> R.drawable.ic_11n
        this == "13d" -> R.drawable.ic_13d
        this == "13n" -> R.drawable.ic_13n
        this == "50d" -> R.drawable.ic_50d
        this == "50n" -> R.drawable.ic_50n
        else -> R.drawable.ic_rainbow
    }
}