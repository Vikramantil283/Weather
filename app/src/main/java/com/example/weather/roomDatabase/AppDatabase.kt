package com.example.weather.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.model.WeatherInfoModel
import com.example.weather.roomDatabase.converter.TypeListConverter
import com.example.weather.roomDatabase.dao.WeatherInfoDao
import com.example.weather.utils.Constants.DB_VERSION

//Database Implementation

@Database(
    entities = [WeatherInfoModel.WeatherInfo::class],
    version = DB_VERSION)
@TypeConverters(TypeListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
}