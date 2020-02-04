package com.sunflower.app

import android.app.Application
import com.sunflower.current_weather.currentWeatherModule
import com.sunflower.location.locationModule
import com.sunflower.weather_forecasts.weatherForecastModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SunflowerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SunflowerApplication)
            modules(listOf(
                appModule,
                locationModule,
                weatherForecastModule,
                currentWeatherModule
            ))
        }
    }
}
