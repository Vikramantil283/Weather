package com.example.weather.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.R
import com.example.weather.model.WeatherInfoModel
import com.example.weather.network.reository.DataRepository
import com.example.weather.roomDatabase.daoImpl.WeatherDaoImpl
import com.example.weather.utils.NetworkUtil.checkNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel()
class WeatherViewModel @Inject constructor(
    private val dataSource: DataRepository, private val weatherDaoImpl: WeatherDaoImpl
) : ViewModel() {

    val responseMutableLiveData: MutableLiveData<List<WeatherInfoModel.WeatherInfo?>?> = MutableLiveData()
    val responseErrorMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        responseErrorMutableLiveData.postValue(exception.message ?: "")
    }

    //Hit the Api and fetch response
    fun callApiForWeatherInfo(context: Context, cityName: String, isFirstTime: Boolean = false) {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                isLoading.postValue(true)
                if (checkNetwork(context)) {
                    if (!isFirstTime) {
                        val response = dataSource.callApiForWeatherInfo(cityName)
                        withContext(Dispatchers.IO) {
                            //Delete the record
                            weatherDaoImpl.deleteWeatherInfo()
                            Timber.d("Message: ${response.message}")
                            if (response.cod == "200") {
                                if (response.list?.isNotEmpty() == true) {
                                    //insert data to DB
                                    weatherDaoImpl.insertWeatherInfo(response.list!!)
                                    responseMutableLiveData.postValue(weatherDaoImpl.getWeatherInfoList(cityName))
                                } else {
                                    responseMutableLiveData.postValue(emptyList())
                                }
                            }
                            isLoading.postValue(false)
                        }
                    } else
                        responseMutableLiveData.postValue(weatherDaoImpl.getWeatherInfoList(cityName))
                }
                isLoading.postValue(false)
            } catch (e: Exception) {
                Toast.makeText(context , context.resources.getText(R.string.server_error) ,Toast.LENGTH_SHORT).show()
                isLoading.postValue(false)
                e.printStackTrace()
            }
        }
    }
}