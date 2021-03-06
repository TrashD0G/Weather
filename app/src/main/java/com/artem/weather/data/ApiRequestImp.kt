package com.artem.weather.data

import android.content.Context
import android.widget.Toast
import com.artem.weather.dagger.DaggerNetworkComponent
import com.artem.weather.domain.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class ApiRequestImp : ApiRequest, CoroutineScope {


    val TOKEN: String = "2c94931ab9b80fa4b073d54ac3e543e8"  //Use your own API key
    val UNITS: String = "metric"                            //For temperature in Celsius


    @Inject
    lateinit var api: ApiOpenWeatherMap

    lateinit var data: WeatherResponse

    init {
          DaggerNetworkComponent.create().injectApiRequestImp(this)
    }


    override suspend fun getReguest(city: String, applicationContext: Context): Boolean {
        try {


            val response = api.getWeather(city, UNITS, TOKEN).awaitResponse()
            if (response.isSuccessful) {
                data = response.body()!!

                return true
            }

            else {
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"City name input error!", Toast.LENGTH_LONG).show()
                }



                return false
            }


        } catch (e: Exception) {


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