package com.sunflower.common

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseApiUrl = "https://api.openweathermap.org/data/2.5/"

val requestInterceptor = Interceptor { chain ->

    val url = chain.request()
        .url()
        .newBuilder()
        .addQueryParameter("appid", BuildConfig.API_KEY)
        .build()
    val request = chain.request()
        .newBuilder()
        .url(url)
        .build()

    return@Interceptor chain.proceed(request)
}

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(requestInterceptor)
    .build()

inline fun <reified T : Any> buildApiClient(): T {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseApiUrl)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)
}
