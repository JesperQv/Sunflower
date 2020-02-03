package com.sunflower.current_weather

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currentWeatherModule = module {
    viewModel { CurrentWeatherViewModel(get(), get()) }
    single<CurrentWeatherRepository> { ApiCurrentWeatherRepository() }
}