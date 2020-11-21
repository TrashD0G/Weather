package com.artem.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.artem.weather.BR
import com.artem.weather.R
import com.artem.weather.dagger.DaggerNetworkComponent
import com.artem.weather.databinding.ActivityWeatherBinding
import com.artem.weather.domain.WeatherModel
import com.artem.weather.domain.WeatherResponse
import kotlinx.android.synthetic.main.activity_weather.*
import javax.inject.Inject


class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var appApiImp:AppApiImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityWeatherBinding = DataBindingUtil.setContentView(this,R.layout.activity_weather)
        val data = intent.getSerializableExtra("DATA_RESPONSE") as WeatherResponse


       DaggerNetworkComponent.create().injectWeatherActivity(this)
        appApiImp.currentTime(data.dt)
        appApiImp.currentWeather(data.weather?.get(0)?.main,imageWeather)


        val weatherModel = WeatherModel(
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

            Toast.makeText(this,"Page refreshed!", Toast.LENGTH_LONG).show()
            swipeRefreshLayout.isRefreshing = false

        }
    }

}