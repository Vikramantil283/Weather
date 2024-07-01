package com.example.weather.network.reository

import com.example.weather.model.WeatherInfoModel

interface DataRepository {

    //Api of weather
    suspend fun callApiForWeatherInfo(cityName: String) : WeatherInfoModel
}