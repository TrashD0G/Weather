package com.artem.weather.data


import android.content.Context
import android.widget.TextView

interface ApiRequest {

    suspend fun getReguest(CITY:String,textView: TextView,applicationContext: Context):Boolean

}