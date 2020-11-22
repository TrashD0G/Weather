package com.artem.weather.data


import android.content.Context

interface ApiRequest {

    suspend fun getReguest(city: String, applicationContext: Context):Boolean
}