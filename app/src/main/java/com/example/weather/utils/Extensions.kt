package com.example.weather.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object Extensions {

    //Convert Kelvin Temp to celsius
    fun Double.kelvinToCelsius(): Int {
        return (this - 273.15).toInt()
    }

    //Hide Keyboard
    fun Activity.hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}