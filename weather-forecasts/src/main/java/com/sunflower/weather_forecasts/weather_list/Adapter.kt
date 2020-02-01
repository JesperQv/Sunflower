package com.sunflower.weather_forecasts.weather_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
            view.context.getDrawable(
                when (forecast.icon) {
                    /*10n & 10d are different icons, we need to define these later
                    they basically indicate light/heavy rain etc, goes for all icons.*/
                    "10n" -> R.drawable.ic_weather_rainy
                    "10d" -> R.drawable.ic_weather_rainy
                    "01n" -> R.drawable.ic_weather_sunny
                    "01d" -> R.drawable.ic_weather_sunny
                    "13n" -> R.drawable.ic_weather_snowy
                    "13d" -> R.drawable.ic_weather_snowy
                    else -> R.drawable.ic_weather_cloudy
                }
            )
        )
        view.setOnClickListener {
            onItemClicked.invoke(forecast)
        }
    }
}
