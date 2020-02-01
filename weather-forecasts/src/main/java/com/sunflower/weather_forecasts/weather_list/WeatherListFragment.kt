package com.sunflower.weather_forecasts.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunflower.weather_forecasts.WeatherForecastNavigator
import com.sunflower.weather_forecasts.R
import com.sunflower.weather_forecasts.data.WeatherApi
import com.sunflower.weather_forecasts.data.toWeatherReportList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class WeatherListFragment : Fragment() {

    private val api = WeatherApi()
    private lateinit var weatherList : RecyclerView
    private lateinit var weatherAdapter : WeatherAdapter
    val navigator: WeatherForecastNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_fragment_list, container, false)
        weatherList = root.findViewById(R.id.weather_list)
        weatherList.layoutManager = LinearLayoutManager(context)
        weatherAdapter = WeatherAdapter() {
            navigator.onForecastClicked(it)
        }
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
