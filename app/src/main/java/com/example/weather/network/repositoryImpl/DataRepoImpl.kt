package com.example.weather.network.repositoryImpl

import com.example.weather.model.WeatherInfoModel
import com.example.weather.network.ApiInterface
import com.example.weather.network.reository.DataRepository
import javax.inject.Inject

class DataRepoImpl @Inject constructor(
    private val apiClient: ApiInterface
) : DataRepository {

    //Api of weather and return the response
    override suspend fun callApiForWeatherInfo(cityName: String): WeatherInfoModel = apiClient.callApiForWeatherInfo(cityName)

}