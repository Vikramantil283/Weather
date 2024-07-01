package com.example.weather.roomDatabase.daoImpl

import com.example.weather.model.WeatherInfoModel
import com.example.weather.roomDatabase.AppDatabase
import javax.inject.Inject

//For WeatherDao Implementation
class WeatherDaoImpl @Inject constructor(private val database: AppDatabase) {

    //insert
    suspend fun insertWeatherInfo(weatherInfoModel: List<WeatherInfoModel.WeatherInfo>) {
        database.weatherInfoDao().insertWeatherInfo(weatherInfoModel)
    }

    //Get Weather list Info
    suspend fun getWeatherInfoList(cityName: String): List<WeatherInfoModel.WeatherInfo?> {
        return database.weatherInfoDao().getWeatherInfoList(cityName)
    }

    //Delete the data
    suspend fun deleteWeatherInfo() {
        return database.weatherInfoDao().deleteWeatherInfo()
    }
}