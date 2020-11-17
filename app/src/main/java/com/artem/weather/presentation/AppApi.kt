package com.artem.weather.presentation

import android.content.Context
import android.widget.EditText


interface AppApi {

    fun inputChecker(checkString: EditText): Boolean
    fun startNewActivity(context: Context)
}