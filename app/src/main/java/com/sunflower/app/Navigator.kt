package com.sunflower.app

import com.sunflower.navigation.BaseNavigator
import com.sunflower.weather_forecasts.view.WeatherForecast
import com.sunflower.weather_forecasts.view.WeatherForecastNavigator

class Navigator : BaseNavigator(),
    WeatherForecastNavigator {
    override fun onForecastClicked(forecast: WeatherForecast) {
        navController?.navigate(R.id.action_weather_fragment_list_to_settings_fragment)
    }
}
