package com.example.weather.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.WeatherInfoModel

// Weather Information Table Dao

@Dao
interface WeatherInfoDao {

    //insert the record
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(movieListModel: List<WeatherInfoModel.WeatherInfo>)

    //fetch list of Weather on cityName
    @Query("SELECT * FROM weatherinfo Where name like  '%'||:cityName||'%'")
    suspend fun getWeatherInfoList(cityName: String): List<WeatherInfoModel.WeatherInfo?>

    //Delete the records
    @Query("Delete from weatherinfo")
    suspend fun deleteWeatherInfo()


}