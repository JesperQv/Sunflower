package com.sunflower.current_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sunflower.common.toWeatherDrawableInt
import kotlinx.android.synthetic.main.fragment_current_weather.*
import org.koin.android.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private val viewModel: CurrentWeatherViewModel by viewModel()
    private lateinit var locationText: TextView
    private lateinit var weatherText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_current_weather, container, false)
        locationText = root.findViewById(R.id.location_title)
        weatherText = root.findViewById(R.id.weather_description)
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            locationText.text = it.locationName
            weatherText.text = it.weatherDescription
            weather_image.setImageDrawable(context?.getDrawable(it.iconId.toWeatherDrawableInt()))
        })
        viewModel.getCurrentWeatherByLocation()
    }
}
