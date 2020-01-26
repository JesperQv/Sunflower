package com.example.sunflower.weather_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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

class WeatherReportViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val weatherConditionView: TextView = view.findViewById(R.id.weather_condition)
    private val weatherLocationView: TextView = view.findViewById(R.id.weather_location)
    private val weatherTempView: TextView = view.findViewById(R.id.weather_temp)
    private val weatherTime: TextView = view.findViewById(R.id.weather_time)
    private val weatherImage: ImageView = view.findViewById(R.id.weather_image)

    fun onBind(report: WeatherReport) {
        weatherConditionView.text = report.weather
        weatherLocationView.text = report.location
        weatherTempView.text = report.temp
        weatherTime.text = report.time
        weatherImage.setImageDrawable(view.context.getDrawable(when(report.icon) {
            "10d" -> R.drawable.ic_weather_rainy
            "01d" -> R.drawable.ic_weather_sunny
            "13n" -> R.drawable.ic_weather_snowy
            else -> R.drawable.ic_weather_cloudy
        }))
        view.setOnClickListener {
            Toast.makeText(
                view.context,
                "It is ${report.weather} in ${report.location} at ${report.time}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
