package com.sunflower.current_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel

class CurrentWeatherFragment: Fragment() {

    private val viewModel: CurrentWeatherViewModel by viewModel()
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_current_weather, container, false)
        textView = root.findViewById(R.id.test)
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            textView.text = it.toString()
        })
        viewModel.getCurrentWeatherByLocation()
    }
}