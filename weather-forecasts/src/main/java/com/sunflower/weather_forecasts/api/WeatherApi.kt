package com.sunflower.weather_forecasts.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6072ef68f569c7fe76e6084d4382126b"
//https://api.openweathermap.org/data/2.5/weather?q=Stockholm&appid=6072ef68f569c7fe76e6084d4382126b&units=metric

interface WeatherApi {

    @GET("forecast")
    fun getCurrentWeatherAsync(
        @Query("q") location: String,
        @Query("units") unitCode: String = "metric"
    ): Deferred<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun getCurrentWeatherAsync(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unitCode: String = "metric"
    ): CurrentWeatherResponse

    companion object {
        //TODO I think we should refactor this into its own class
        operator fun invoke(): WeatherApi {

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
                .create(WeatherApi::class.java)
        }
    }
}


