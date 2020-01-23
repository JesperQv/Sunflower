package com.example.sunflower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower.list_example.ListFragment
import com.example.sunflower.weather_list.WeatherListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = WeatherListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()

    }
}

