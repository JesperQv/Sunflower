package com.example.sunflower.weather_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunflower.R

class WeatherAdapter : RecyclerView.Adapter<WeatherReportViewHolder>() {

    private var reports: ArrayList<WeatherReport> = ArrayList()

    fun setWeatherReports(newReports: List<WeatherReport>) {
        reports.clear()
        reports.addAll(newReports)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherReportViewHolder {
        return WeatherReportViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.weather_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = reports.size

    override fun onBindViewHolder(holder: WeatherReportViewHolder, position: Int) {
        holder.onBind(reports[position])
    }
}

class WeatherReportViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val weatherConditionView: TextView = view.findViewById(R.id.weather_condition)
    private val weatherLocationView: TextView = view.findViewById(R.id.weather_location)
    private val weatherTempView: TextView = view.findViewById(R.id.weather_temp)
    private val weatherImage: ImageView = view.findViewById(R.id.weather_image)

    fun onBind(weather: WeatherReport) {
        weatherConditionView.text = weather.weather
        weatherLocationView.text = weather.location
        weatherTempView.text = weather.temp.toString()
        Glide.with(view).load(weather.weatherUrl).into(weatherImage)
        view.setOnClickListener {
            Toast.makeText(view.context, "It is ${weather.weather} in ${weather.location}", Toast.LENGTH_SHORT).show()
        }
    }
}
