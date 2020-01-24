package com.example.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sunflower.data.WeatherApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyFragment : Fragment() {

    private var stepSize = 1
    private var counterToDisplay = 0
    private lateinit var textView : TextView
    private lateinit var testImage : ImageView
    private lateinit var counter : TextView
    private lateinit var incButton : Button
    private lateinit var decButton : Button
    private lateinit var chButton : Button
    private lateinit var editText : EditText
    private var stepSizeKey = "step_size"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val pref = activity?.getSharedPreferences("sunflower", 0)
        stepSize = pref!!.getInt(stepSizeKey, 1)

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        editText = view.findViewById(R.id.edit_txt)
        editText.setText(stepSize.toString())
        textView = view.findViewById(R.id.weather_test)
        testImage = view.findViewById(R.id.test_image)
        counter = view.findViewById(R.id.inc_num)
        chButton = view.findViewById(R.id.ch_button)
        incButton = view.findViewById(R.id.inc_button)
        decButton = view.findViewById(R.id.dec_button)
        counter.text = counterToDisplay.toString()

        incButton.setOnClickListener {
            increment(stepSize)
        }
        decButton.setOnClickListener {
            decrement(stepSize)

        }
        chButton.setOnClickListener {
            stepSize = editText.text.toString().toInt()
            pref.edit().putInt(stepSizeKey, stepSize).apply()
        }

        Glide.with(this)
            .load("https://i.imgur.com/Wk5rb6I.png")
            .into(testImage)

        //This is shit practice and only to test if WeatherApiInterface works etc
        val apiService = WeatherApiInterface()

        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = apiService.getCurrentWeather("Stockholm").await()
            textView.text = currentWeatherResponse.weather.toString()
        }
        //end of shit

        return view
    }

    private fun increment(incWith: Int) {
        counterToDisplay += incWith
        counter.text = counterToDisplay.toString()
    }

    private fun decrement(decWith: Int) {
        if ((counterToDisplay - decWith) >= 0) {
            counterToDisplay -= decWith
            counter.text = counterToDisplay.toString()

        }
        else {
            counterToDisplay = 0
            counter.text = counterToDisplay.toString()
        }
    }
}
