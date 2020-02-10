package com.sunflower.weather_forecasts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunflower.weather_forecasts.R
import com.sunflower.weather_forecasts.databinding.WeatherListItemBinding

class WeatherAdapter(private val onItemClicked: (forecast: WeatherForecast) -> Unit) :
    RecyclerView.Adapter<WeatherReportViewHolder>() {

    private var forecasts: ArrayList<WeatherForecast> = ArrayList()

    fun setWeatherReports(newForecasts: List<WeatherForecast>) {
        forecasts.clear()
        forecasts.addAll(newForecasts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherReportViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<WeatherListItemBinding>(layoutInflater,
            R.layout.weather_list_item,
            parent,
            false)
        return WeatherReportViewHolder(binding)
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: WeatherReportViewHolder, position: Int) {
        holder.onBind(forecasts[position], onItemClicked)
    }
}

class WeatherReportViewHolder(private val binding: WeatherListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(forecast: WeatherForecast, onItemClicked: (forecast: WeatherForecast) -> Unit) {

        binding.forecast = forecast
        binding.root.setOnClickListener {
            onItemClicked.invoke(forecast)
        }
    }
}
