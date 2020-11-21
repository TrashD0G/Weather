package com.artem.weather.data


import android.content.Context

interface ApiRequest {

    suspend fun getReguest(CITY:String,applicationContext: Context):Boolean
}