package com.sunflower.current_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sunflower.current_weather.databinding.FragmentCurrentWeatherBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private val viewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_current_weather,
            container,
            false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            binding.report = it
        })
        viewModel.getCurrentWeatherByLocation()
    }
}
