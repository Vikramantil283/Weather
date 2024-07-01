package com.example.weather.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {

    //check network connection
    fun checkNetwork(context: Context?): Boolean {
        return if (context != null) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) true else activeNetwork.type == ConnectivityManager.TYPE_MOBILE
            } else false

        } else {
            false
        }
    }
}