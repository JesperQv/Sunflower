package com.example.sunflower.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower.R

class WeatherListFragment : Fragment() {

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
        val reports = ArrayList<WeatherReport>()
        for (i in 0..50) {
            reports.addAll(generateReports())
        }
        weatherAdapter.setWeatherReports(reports)

        return root
    }

    private fun generateReports(): ArrayList<WeatherReport> {

        val list = listOf(
            WeatherReport(
                location = "Eskilstuna",
                weather = "Cloudy",
                temp = 5.0,
                weatherUrl = "https://i.imgur.com/hNP0YG5.png"
            ),
            WeatherReport(
                location = "Stockholm",
                weather = "Snowy",
                temp = -3.1,
                weatherUrl = "https://i.imgur.com/MfoMW03.png"
            ),
            WeatherReport(
                location = "Uppsala",
                weather = "Sunny",
                temp = 10.3,
                weatherUrl = "https://i.imgur.com/FRzrbiW.png"
            ),
            WeatherReport(
                location = "Puerto de la Cruz",
                weather = "Extreme heat",
                temp = 31.5,
                weatherUrl = "https://i.imgur.com/897KcBb.png"
            ),
            WeatherReport(
                location = "Ljusterö",
                weather = "Sunny with rain",
                temp = 7.3,
                weatherUrl = "https://i.imgur.com/z0maGj6.png"
            ),
            WeatherReport(
                location = "Copenhagen",
                weather = "Warm sunset",
                temp = 15.0,
                weatherUrl = "https://i.imgur.com/EoJpVdf.png"
            ),
            WeatherReport(
                location = "London",
                weather = "Extreme rain",
                temp = 1.0,
                weatherUrl = "https://i.imgur.com/aYOV4eo.png"
            ),
            WeatherReport(
                location = "Moscow",
                weather = "Partly cloudy",
                temp = 5.3,
                weatherUrl = "https://i.imgur.com/Yoe8SSE.png"
            ),
            WeatherReport(
                location = "New York",
                weather = "Starry Night",
                temp = 3.4,
                weatherUrl = "https://i.imgur.com/lHmwVgC.png"
            ),
            WeatherReport(
                location = "Kyoto",
                weather = "Sunny",
                temp = 2.0,
                weatherUrl = "https://i.imgur.com/FRzrbiW.png"
            )
        )
        val arrayList = ArrayList<WeatherReport>()
        arrayList.addAll(list)
        return arrayList
    }
}
