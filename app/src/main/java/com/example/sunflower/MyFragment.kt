package com.example.sunflower

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_fragment.*

class MyFragment : Fragment() {

    private var stepSize = 1
    private var counterToDisplay = 0
    lateinit var counter : TextView
    lateinit var incButton : Button
    lateinit var decButton : Button
    lateinit var chButton : Button
    lateinit var editText : EditText
    private var stepSizeKey = "step_size"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val pref = activity?.getSharedPreferences("sunflower", 0)
        stepSize = pref!!.getInt(stepSizeKey, 1)



        val view = inflater.inflate(R.layout.main_fragment, container, false)

        editText = view.findViewById<EditText>(R.id.edit_txt)
        editText.setText(stepSize.toString())


        counter = view.findViewById<TextView>(R.id.inc_num)
        chButton = view.findViewById<Button>(R.id.ch_button)
        incButton = view.findViewById<Button>(R.id.inc_button)
        decButton = view.findViewById<Button>(R.id.dec_button)
        counter.text = counterToDisplay.toString()

        incButton.setOnClickListener {
            increment(stepSize)
        }
        decButton.setOnClickListener {
            decrement(stepSize)

        }
        chButton.setOnClickListener {
            stepSize = editText.text.toString().toInt()
            pref.edit().putInt(stepSizeKey, stepSize).commit()
        }

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