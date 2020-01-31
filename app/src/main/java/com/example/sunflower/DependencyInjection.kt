package com.example.sunflower

import com.example.weather_forecasts.WeatherForecastNavigator
import org.koin.dsl.module

val navigator = Navigator()

val appModule = module {
    single { navigator }
    single<WeatherForecastNavigator> { navigator }
}