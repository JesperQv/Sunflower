package com.example.sunflower.navigation_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sunflower.R

class TestFragment : Fragment() {

    private lateinit var testImage : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.test_fragment, container, false)

        testImage = view.findViewById(R.id.test_image)
        Glide.with(this)
            .load("https://i.imgur.com/Wk5rb6I.png")
            .into(testImage)

        return view
    }
}