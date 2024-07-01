package com.example.weather.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.utils.DateUtils
import com.example.weather.utils.Extensions.hideKeyboard
import com.example.weather.utils.Extensions.kelvinToCelsius
import com.example.weather.viewModel.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        onClickListener()
        onObserver()
    }

    private fun initUI() {
        //Call api when activity create
        viewModel.callApiForWeatherInfo(this@MainActivity, binding.cityInput.text.toString(), true)
    }

    private fun onClickListener() {
        binding.apply {
            searchButton.setOnClickListener {
                //Hide Keyboard onClick
                this@MainActivity.hideKeyboard()
                if(cityInput.text.trim().isNotEmpty())
                viewModel.callApiForWeatherInfo(this@MainActivity, binding.cityInput.text.toString())
                else Snackbar.make(it , it.resources.getString(R.string.enter_city_name) , Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    //Observe LiveData
    private fun onObserver() {
        viewModel.apply {
            //Response from API
            responseMutableLiveData.observe(this@MainActivity) {
                binding.layoutWeatherInfo.apply {
                    if (it?.isNotEmpty() == true) {
                        binding.noDataFound.visibility = View.GONE
                        parent.visibility = View.VISIBLE
                        tvDateTime.text = DateUtils.getToday("dd-MM-yyyy hh:mm:ss")
                        val temp = it[0]?.main?.temp ?: 0.0
                        tvTemperature.text = temp.kelvinToCelsius().toString()
                        tvCityCountry.text = it[0]?.name?.plus("," + it[0]?.sys?.country)
                        tvHumidityValue.text = it[0]?.main?.humidity.toString().plus("%")
                        tvPressureValue.text = it[0]?.main?.pressure.toString().plus(" mBar")
                    } else {
                        binding.noDataFound.visibility = View.VISIBLE
                        parent.visibility = View.GONE
                    }
                }
            }

            //Any Error
            responseErrorMutableLiveData.observe(this@MainActivity) {
                binding.pbLoading.visibility = View.GONE
            }

            //show/Hide Loading progress
            isLoading.observe(this@MainActivity) {
                if (it) binding.pbLoading.visibility = View.VISIBLE
                else binding.pbLoading.visibility = View.GONE
            }
        }
    }
}

