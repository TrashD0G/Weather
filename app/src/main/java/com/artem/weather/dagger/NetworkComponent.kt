package com.artem.weather.dagger

import com.artem.weather.data.ApiRequestImp
import com.artem.weather.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent{

    fun injectMainActivity(mainActivity: MainActivity)
    fun injectApiRequestImp(apiRequestImp: ApiRequestImp)
}