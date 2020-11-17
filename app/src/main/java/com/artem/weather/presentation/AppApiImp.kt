package com.artem.weather.presentation

import android.content.Context
import android.content.Intent
import android.widget.EditText


class AppApiImp:AppApi {

    override fun inputChecker(checkString: EditText): Boolean {
        val checkerCITY:String = checkString.text.toString()

        if(checkerCITY.trim().isNotEmpty()){
            return true
        }

        return false
    }

    override fun startNewActivity(context: Context) {
        val intent = Intent(context, WeatherActivity::class.java)
        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }


}