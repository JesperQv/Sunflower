package com.sunflower.location

import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    single<LocationListener> { CachedLocationProvider() } bind LocationProvider::class
}
