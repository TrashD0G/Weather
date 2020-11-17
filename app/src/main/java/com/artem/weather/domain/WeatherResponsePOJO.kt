package com.artem.weather.domain

import com.google.gson.annotations.SerializedName


class WeatherResponse {

    @SerializedName("sys")
    var sys: Sys? = null

    @SerializedName("main")
    var main: Main? = null

    @SerializedName("wind")
    var wind: Wind? = null

    @SerializedName("timezone")
    var timezone:Int = 0

    @SerializedName("name")
    var name:String = "null"

}

class Main {

    @SerializedName("temp")
    var temp: Float = 0.0f
    @SerializedName("humidity")
    var humidity: Float = 0.0f
    @SerializedName("pressure")
    var pressure: Float = 0.0f
    @SerializedName("temp_min")
    var temp_min: Float = 0.0f
    @SerializedName("temp_max")
    var temp_max: Float = 0.0f
    @SerializedName("feels_like")
    var feels_like:Float = 0.0f


}

class Sys {
    @SerializedName("country")
    var country: String? = null
}

class Wind{
    @SerializedName("speed")
    var speed:Float = 0.0f
    @SerializedName("deg")
    var deg:Float = 0.0f
}
