package com.artem.weather.data

import com.artem.weather.domain.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiOpenWeatherMap {

    @GET("data/2.5/weather?")
    fun getWeather(@Query("q") city: String, @Query("units") units: String, @Query("appid") token: String ) : Call<WeatherResponse>
}