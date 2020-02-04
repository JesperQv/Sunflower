package com.sunflower.weather_forecasts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunflower.common.toWeatherDrawableInt
import com.sunflower.weather_forecasts.R

class WeatherAdapter(private val onItemClicked: (forecast: WeatherForecast) -> Unit) :
    RecyclerView.Adapter<WeatherReportViewHolder>() {

    private var forecasts: ArrayList<WeatherForecast> = ArrayList()

    fun setWeatherReports(newForecasts: List<WeatherForecast>) {
        forecasts.clear()
        forecasts.addAll(newForecasts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherReportViewHolder {
        return WeatherReportViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.weather_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: WeatherReportViewHolder, position: Int) {
        holder.onBind(forecasts[position], onItemClicked)
    }
}

class WeatherReportViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val weatherConditionView: TextView = view.findViewById(R.id.weather_condition)
    private val weatherLocationView: TextView = view.findViewById(R.id.weather_location)
    private val weatherTempView: TextView = view.findViewById(R.id.weather_temp)
    private val weatherTime: TextView = view.findViewById(R.id.weather_time)
    private val weatherImage: ImageView = view.findViewById(R.id.weather_image)

    fun onBind(forecast: WeatherForecast, onItemClicked: (forecast: WeatherForecast) -> Unit) {
        weatherConditionView.text = forecast.weather
        weatherLocationView.text = forecast.location
        weatherTempView.text = forecast.temp
        weatherTime.text = forecast.time
        weatherImage.setImageDrawable(
            view.context.getDrawable(forecast.icon.toWeatherDrawableInt())
        )
        view.setOnClickListener {
            onItemClicked.invoke(forecast)
        }
    }
}
