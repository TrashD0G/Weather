package com.artem.weather.dagger


import com.artem.weather.data.ApiOpenWeatherMap
import com.artem.weather.data.ApiRequestImp
import com.artem.weather.presentation.AppApiImp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {



    @Provides
    fun providesGson(): Gson = GsonBuilder().create()


    @Provides
    fun providesAppApiImp() : AppApiImp{
        return AppApiImp()
    }


    @Provides
    fun providesApiRequestImp() : ApiRequestImp{
        return ApiRequestImp()
    }




    @Singleton
    @Provides
  fun providesRetrofit(gson: Gson) : ApiOpenWeatherMap {
      return Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build()
          .create(ApiOpenWeatherMap::class.java)
  }


}