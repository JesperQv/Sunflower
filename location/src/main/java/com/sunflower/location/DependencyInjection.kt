package com.sunflower.location

import android.location.Geocoder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.*

val locationModule = module {
    single<LocationListener> { CachedLocationProvider(get()) } bind LocationProvider::class
    single { Geocoder(androidContext(), Locale.getDefault()) }
}