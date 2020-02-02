package com.sunflower.weather_forecasts.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunflower.weather_forecasts.WeatherForecastNavigator
import com.sunflower.weather_forecasts.R
import com.sunflower.weather_forecasts.WeatherForecastViewModel
import org.koin.android.ext.android.inject

class WeatherListFragment : Fragment() {

    private lateinit var weatherList : RecyclerView
    private lateinit var weatherAdapter : WeatherAdapter
    val navigator: WeatherForecastNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_fragment_list, container, false)
        val model: WeatherForecastViewModel by viewModels()
        weatherList = root.findViewById(R.id.weather_list)
        weatherList.layoutManager = LinearLayoutManager(context)
        weatherAdapter = WeatherAdapter() {
            navigator.onForecastClicked(it)
        }
        weatherList.adapter = weatherAdapter
        model.forecasts.observe(viewLifecycleOwner, Observer<List<WeatherForecast>> { forecasts ->
            weatherAdapter.setWeatherReports(forecasts)
        })

        return root
    }
}
