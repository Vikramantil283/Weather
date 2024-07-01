package com.example.weather.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {

    //Get Today Date in given format
    fun getToday(format: String?): String {
        val instance = Calendar.getInstance()
        val time = instance.time
        return try {
            val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
            simpleDateFormat.format(time)
        } catch (e: Exception) {
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            simpleDateFormat.format(time)
        }
    }
}