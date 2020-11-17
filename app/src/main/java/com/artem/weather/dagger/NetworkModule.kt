package com.artem.weather.dagger


import com.artem.weather.data.ApiOpenWeatherMap
import com.artem.weather.data.ApiRequestImp
import com.artem.weather.presentation.AppApiImp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    fun provideAppApiImp():AppApiImp{
        return AppApiImp()
    }


    @Provides
    fun provideApiRequestImp():ApiRequestImp{
        return ApiRequestImp()
    }


    @Singleton
    @Provides
  fun provideRetrofit(): ApiOpenWeatherMap {
      return Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(ApiOpenWeatherMap::class.java)
  }


}