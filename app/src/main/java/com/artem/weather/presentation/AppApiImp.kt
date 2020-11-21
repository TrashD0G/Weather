package com.artem.weather.presentation

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.ImageView
import com.artem.weather.R
import com.artem.weather.domain.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*


class AppApiImp:AppApi {

    lateinit var currentTime:String


    override fun inputChecker(checkString: EditText): Boolean {
        val checkerCITY:String = checkString.text.toString()

        if(checkerCITY.trim().isNotEmpty()){
            return true
        }

        return false
    }

    override fun startNewActivity(context: Context,data:WeatherResponse) {
        val intent = Intent(context, WeatherActivity::class.java)
        intent.putExtra("DATA_RESPONSE",data)
        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    override fun currentTime(rawDate: Int):String {

            val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

            val timeDate = Date(rawDate * 1000L )

            currentTime  = sdf.format(timeDate)

            return currentTime

    }

    override fun currentWeather(rawWeather: String?,imageWeather:ImageView) {

        when(rawWeather){
            "Thunderstorm" -> imageWeather.setBackgroundResource(R.drawable.thunder)
            "Drizzle" -> imageWeather.setBackgroundResource(R.drawable.drizzle)
            "Rain" -> imageWeather.setBackgroundResource(R.drawable.drizzle)
            "Snow" -> imageWeather.setBackgroundResource(R.drawable.snow)
            "Mist","Smoke","Haze","Dust","Fog","Sand","Ash","Squall","Tornado", -> imageWeather.setBackgroundResource(R.drawable.fog)
            "Clear"-> imageWeather.setBackgroundResource(R.drawable.sun)
            "Clouds" -> imageWeather.setBackgroundResource(R.drawable.cloud)
        }

    }


}