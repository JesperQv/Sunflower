package com.sunflower.app

import com.sunflower.weather_forecasts.view.WeatherForecastNavigator
import org.koin.dsl.module

val navigator = Navigator()

val appModule = module {
    single { navigator }
    single<WeatherForecastNavigator> { navigator }
}