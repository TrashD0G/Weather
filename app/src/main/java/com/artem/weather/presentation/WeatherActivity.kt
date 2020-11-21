package com.artem.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.artem.weather.BR
import com.artem.weather.R
import com.artem.weather.dagger.DaggerNetworkComponent
import com.artem.weather.data.ApiRequestImp
import com.artem.weather.databinding.ActivityWeatherBinding
import com.artem.weather.domain.WeatherModel
import com.artem.weather.domain.WeatherResponse
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class WeatherActivity : AppCompatActivity(), CoroutineScope {

    @Inject
    lateinit var appApiImp:AppApiImp

    @Inject
    lateinit var apiRequestImp: ApiRequestImp

    lateinit var weatherModel:WeatherModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityWeatherBinding = DataBindingUtil.setContentView(this,R.layout.activity_weather)
        val data = intent.getSerializableExtra("DATA_RESPONSE") as WeatherResponse


       DaggerNetworkComponent.create().injectWeatherActivity(this)
        appApiImp.currentTime(data.dt)
        appApiImp.currentWeather(data.weather?.get(0)?.main,imageWeather)


          weatherModel = WeatherModel(
                data.name,
                data.main?.temp.toString() + "\u2103",
                appApiImp.currentTime,
                data.main?.feels_like.toString(),
                data.main?.pressure.toString(),
                data.main?.humidity.toString(),
                data.wind?.speed.toString(),
                data.weather?.get(0)?.main.toString()
            )


        binding.setVariable(BR.weatherModel,weatherModel)



        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swiperefresh)

        swipeRefreshLayout.setOnRefreshListener {

            launch(Dispatchers.IO){
                apiRequestImp.getReguest(data.name,applicationContext)

                appApiImp.currentTime(apiRequestImp.data.dt)
                appApiImp.currentWeather(apiRequestImp.data.weather?.get(0)?.main,imageWeather)



                withContext(Dispatchers.Main){
                      weatherModel = WeatherModel(
                         apiRequestImp.data.name,
                         apiRequestImp.data.main?.temp.toString() + "\u2103",
                        appApiImp.currentTime,
                         apiRequestImp.data.main?.feels_like.toString(),
                         apiRequestImp.data.main?.pressure.toString(),
                         apiRequestImp.data.main?.humidity.toString(),
                         apiRequestImp.data.wind?.speed.toString(),
                         apiRequestImp.data.weather?.get(0)?.main.toString()
                    )


                    binding.setVariable(BR.weatherModel,weatherModel)
                    
                    swipeRefreshLayout.isRefreshing = false
                }
            }


        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}