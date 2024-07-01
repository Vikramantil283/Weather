package com.example.weather.roomDatabase.converter

import androidx.room.TypeConverter
import com.example.weather.model.WeatherInfoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Room DB Type Converter
class TypeListConverter {

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) {
            return null
        }

        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<Int>?): String? {
        if (value == null) {
            return null
        }
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromMain(main: WeatherInfoModel.WeatherInfo.Main?): String? {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun toMain(mainString: String?): WeatherInfoModel.WeatherInfo.Main? {
        return Gson().fromJson(mainString, object : TypeToken<WeatherInfoModel.WeatherInfo.Main>() {}.type)
    }

    @TypeConverter
    fun fromSys(sys: WeatherInfoModel.WeatherInfo.Sys?): String? {
        return Gson().toJson(sys)
    }

    @TypeConverter
    fun toSys(sysString: String?): WeatherInfoModel.WeatherInfo.Sys? {
        return Gson().fromJson(sysString, object : TypeToken<WeatherInfoModel.WeatherInfo.Sys>() {}.type)
    }

    @TypeConverter
    fun fromWind(wind: WeatherInfoModel.WeatherInfo.Wind?): String? {
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun toWind(windString: String?): WeatherInfoModel.WeatherInfo.Wind? {
        return Gson().fromJson(windString, object : TypeToken<WeatherInfoModel.WeatherInfo.Wind>() {}.type)
    }

    @TypeConverter
    fun fromClouds(clouds: WeatherInfoModel.WeatherInfo.Clouds?): String? {
        return Gson().toJson(clouds)
    }

    @TypeConverter
    fun toClouds(cloudsString: String?): WeatherInfoModel.WeatherInfo.Clouds? {
        return Gson().fromJson(cloudsString, object : TypeToken<WeatherInfoModel.WeatherInfo.Clouds>() {}.type)
    }


}