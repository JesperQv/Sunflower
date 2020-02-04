package com.sunflower.weather_forecasts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunflower.weather_forecasts.R
import com.sunflower.weather_forecasts.viewmodel.WeatherForecastViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class WeatherForecastFragment : Fragment() {

    private var cityToSearch = "Stockholm"
    private var cityToSearchKey = "city_to_search"
    private lateinit var weatherSearchBar : EditText
    private lateinit var weatherSearchButton : Button
    private lateinit var weatherList : RecyclerView
    private lateinit var weatherAdapter : WeatherAdapter
    private val navigator: WeatherForecastNavigator by inject()
    private val model: WeatherForecastViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val pref = activity?.getSharedPreferences("sunflower", 0)
        cityToSearch = pref!!.getString(cityToSearchKey, "Stockholm").toString()

        val root = inflater.inflate(R.layout.weather_fragment_list, container, false)

        weatherSearchButton = root.findViewById(R.id.search_button)
        weatherSearchBar = root.findViewById(R.id.edit_search)
        weatherSearchBar.setText(cityToSearch)


        weatherList = root.findViewById(R.id.weather_list)
        weatherList.layoutManager = LinearLayoutManager(context)
        weatherAdapter = WeatherAdapter() {
            navigator.onForecastClicked(it)
        }
        weatherList.adapter = weatherAdapter
        weatherSearchButton.setOnClickListener {
            cityToSearch = weatherSearchBar.text.toString()
            pref.edit().putString(cityToSearchKey, cityToSearch).apply()
            model.getWeatherForecastBySearch(cityToSearch)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        model.forecasts.observe(viewLifecycleOwner, Observer<List<WeatherForecast>> { forecasts ->
            weatherAdapter.setWeatherReports(forecasts)
        })
        model.getWeatherForecastBySearch(cityToSearch)
    }

}
