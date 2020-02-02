package com.sunflower.weather_forecasts

import com.sunflower.weather_forecasts.repository.CachedWeatherForecastRepository
import com.sunflower.weather_forecasts.repository.WeatherForecastRepository
import com.sunflower.weather_forecasts.viewmodel.WeatherForecastViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherForecastModule = module {
    viewModel { WeatherForecastViewModel(get()) }
    single<WeatherForecastRepository> { CachedWeatherForecastRepository() }
}