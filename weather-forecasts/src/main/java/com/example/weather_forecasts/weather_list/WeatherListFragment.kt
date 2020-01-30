package com.example.weather_forecasts.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecasts.R
import com.example.weather_forecasts.data.WeatherApi
import com.example.weather_forecasts.data.toWeatherReportList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherListFragment : Fragment() {

    private val api = WeatherApi()
    private lateinit var weatherList : RecyclerView
    private lateinit var weatherAdapter : WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_fragment_list, container, false)
        weatherList = root.findViewById(R.id.weather_list)
        weatherList.layoutManager = LinearLayoutManager(context)
        weatherAdapter = WeatherAdapter()
        weatherList.adapter = weatherAdapter

        return root
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = api.getCurrentWeatherAsync("Stockholm,SE").await()
            weatherAdapter.setWeatherReports(currentWeatherResponse.toWeatherReportList())
        }
    }
}
