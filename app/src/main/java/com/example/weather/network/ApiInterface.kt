package com.example.weather.network

import com.example.weather.model.WeatherInfoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //Api of weather with endpoint
    @GET(URL.WEATHER_API)
    suspend fun callApiForWeatherInfo(
        @Query("q") cityName: String,
        @Query("appid") appId: String = URL.APP_ID,
        @Query("unit") unit: String = URL.UNIT
    ): WeatherInfoModel
}