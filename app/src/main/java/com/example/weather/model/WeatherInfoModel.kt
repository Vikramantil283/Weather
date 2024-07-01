package com.example.weather.model


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// For Weather Module
@Keep
data class WeatherInfoModel(
    var message: String? = null, // accurate
    var cod: String? = null, // 200
    var count: Int? = null, // 1
    var list: List<WeatherInfo>? = emptyList()
) {
    //Create table name of weatherInfo
    @Entity(tableName = "weatherinfo")
    @Keep
    data class WeatherInfo(
        @PrimaryKey(autoGenerate = false) val id: Int, // 1255744
        var name: String? = null, // Sonīpat
        var main: Main? = null,
        var sys: Sys? = null,
        var wind: Wind? = null,
        var clouds: Clouds? = null,
    ) {
        @Keep
        data class Main(
            var temp: Double? = null, // 308.07
            @SerializedName("feels_like") var feelsLike: Double? = null, // 312.88
            @SerializedName("temp_min") var tempMin: Double? = null, // 308.07
            @SerializedName("temp_max") var tempMax: Double? = null, // 308.07
            var pressure: Int? = null, // 998
            var humidity: Int? = null, // 48
            @SerializedName("sea_level") var seaLevel: Int? = null, // 998
            @SerializedName("grnd_level") var grndLevel: Int? = null // 973
        )

        @Keep
        data class Wind(
            var speed: Double? = null, // 2.17
            var deg: Int? = null // 131
        )

        @Keep
        data class Sys(
            var country: String? = null // IN
        )

        @Keep
        data class Clouds(
            var all: Int? = null // 100
        )
    }
}