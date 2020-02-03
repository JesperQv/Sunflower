package com.sunflower.current_weather.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6072ef68f569c7fe76e6084d4382126b"

interface CurrentWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeatherAsync(
        @Query("q") location: String,
        @Query("units") unitCode: String = "metric"
    ): CurrentWeatherResponse

    @GET("weather")
    suspend fun getCurrentWeatherAsync(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unitCode: String = "metric"
    ): CurrentWeatherResponse

    companion object {
        operator fun invoke(): CurrentWeatherApi {

            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrentWeatherApi::class.java)
        }
    }
}