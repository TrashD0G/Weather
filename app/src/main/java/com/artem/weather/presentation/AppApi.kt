package com.artem.weather.presentation

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import com.artem.weather.domain.WeatherResponse


interface AppApi {

    fun inputChecker(checkString: EditText): Boolean
    fun startNewActivity(context: Context, data: WeatherResponse)
    fun currentTime(rawDate: Int): String
    fun currentWeather(rawWeather: String?, imageWeather: ImageView)
}