package com.artem.weather.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.artem.weather.R
import com.artem.weather.dagger.DaggerNetworkComponent
import com.artem.weather.data.ApiRequestImp
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext



class MainActivity : AppCompatActivity(),CoroutineScope {


    @Inject
    lateinit var appApiImp: AppApiImp

    @Inject
    lateinit var apiRequestImp: ApiRequestImp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRespon: Button = findViewById(R.id.buttonRespon)
        val menuText: TextView = findViewById(R.id.menu_text)
        val cityName:EditText = findViewById(R.id.cityName)




        DaggerNetworkComponent.create().injectMainActivity(this)



        btnRespon.setOnClickListener {

            if (appApiImp.inputChecker(cityName)){

                val city = cityName.text.toString()

                launch(Dispatchers.IO) {
                    if(apiRequestImp.getReguest(city,menuText,applicationContext)){

                        appApiImp.startNewActivity(applicationContext,apiRequestImp.data)
                    }

                }
            }
            else{
                Toast.makeText(applicationContext,"Enter the name of the city!",Toast.LENGTH_LONG).show()
            }

        }





    }



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


}



