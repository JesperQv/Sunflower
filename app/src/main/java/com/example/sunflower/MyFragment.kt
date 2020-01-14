package com.example.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {

    private var stepSize = 1
    private var counterToDisplay = 0
    lateinit var counter : TextView
    lateinit var incButton : Button
    lateinit var decButton : Button
    lateinit var chButton : Button
    lateinit var editText : EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.main_fragment, container, false)

        editText = view.findViewById<EditText>(R.id.edit_txt)
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