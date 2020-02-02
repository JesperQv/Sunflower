package com.sunflower.app

import com.sunflower.navigation.BaseNavigator
import com.sunflower.weather_forecasts.WeatherForecastNavigator
import com.sunflower.weather_forecasts.weather_list.WeatherForecast

class Navigator : BaseNavigator(), WeatherForecastNavigator {
    override fun onForecastClicked(forecast: WeatherForecast) {
        navController?.navigate(R.id.weather_report_to_main_fragment)
    }
}