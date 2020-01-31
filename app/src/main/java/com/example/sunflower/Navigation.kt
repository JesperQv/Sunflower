package com.example.sunflower

import androidx.navigation.NavController
import com.example.weather_forecasts.WeatherForecastNavigator
import com.example.weather_forecasts.weather_list.WeatherForecast

class Navigator : BaseNavigator(), WeatherForecastNavigator {
    override fun onForecastClicked(forecast: WeatherForecast) {
        navController?.navigate(R.id.weather_report_to_main_fragment)
    }
}

abstract class BaseNavigator {

    protected var navController: NavController? = null

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }
}