package com.artem.weather.data

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.artem.weather.dagger.DaggerNetworkComponent
import com.artem.weather.presentation.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class ApiRequestImp():ApiRequest, CoroutineScope {


    val TOKEN: String = "2c94931ab9b80fa4b073d54ac3e543e8"  //Use your own API key
    val UNITS: String = "metric"                            //For temperature in Celsius


    @Inject
    lateinit var api:ApiOpenWeatherMap


    init {
          DaggerNetworkComponent.create().injectApiRequestImp(this)
    }


    override suspend fun getReguest(CITY: String, textView: TextView,applicationContext:Context): Boolean {
        try {


            val response = api.getWeather(CITY, UNITS, TOKEN).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!


                withContext(Dispatchers.Main){

                }


                Log.i(TAG,"feels like " + data.main?.feels_like.toString())
                Log.i(TAG,"temp " + data.main?.temp.toString())

                Log.i(TAG,"wind speed " + data.wind?.speed.toString())
                Log.i(TAG,"wind deg " + data.wind?.deg.toString())

                Log.i(TAG,"timezone "  + data.timezone.toString())

                Log.i(TAG,"name "  + data.name)




                return true
            }

            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"City name input error!", Toast.LENGTH_LONG).show()
                }

                return false

            }


        } catch (e: Exception) {

            Log.i(TAG, "Error!")

            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext,"No internet connection or site not responding!",
                    Toast.LENGTH_LONG).show()

            }

            return false


        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


}