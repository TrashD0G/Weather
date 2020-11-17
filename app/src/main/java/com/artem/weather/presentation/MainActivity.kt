package com.artem.weather.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.artem.weather.R
import com.artem.weather.dagger.DaggerNetworkComponent


import com.artem.weather.data.ApiOpenWeatherMap
import com.artem.weather.data.ApiRequest
import com.artem.weather.data.ApiRequestImp
import kotlinx.coroutines.*

import retrofit2.awaitResponse
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


val TAG = "MyTag"

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

                val CITY = cityName.text.toString()

                launch(Dispatchers.IO) {
                    if(apiRequestImp.getReguest(CITY,menuText,applicationContext)){
                        appApiImp.startNewActivity(applicationContext)
                    }

                }
            }
            else{
                Toast.makeText(applicationContext,"Enter the name of the city!",Toast.LENGTH_LONG).show()
            }

        }


    }


/*
    override suspend fun getReguest(CITY:String,textView: TextView): Boolean {


        try {
            val response = api.getWeather(CITY, UNITS, TOKEN).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!


                withContext(Dispatchers.Main){

                }


                return true
            }

            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"City name input error!",Toast.LENGTH_LONG).show()
                }

                return false

            }


        } catch (e: Exception) {

            Log.i(TAG, "Error!")
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext,"No internet connection or site not responding!",Toast.LENGTH_LONG).show()

            }

            return false


        }


    }
*/

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


}



