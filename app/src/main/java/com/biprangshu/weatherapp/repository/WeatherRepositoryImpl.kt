package com.biprangshu.weatherapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.biprangshu.weatherapp.data.mappers.toWeatherInfo
import com.biprangshu.weatherapp.data.remote.WeatherApi
import com.biprangshu.weatherapp.domain.respository.WeatherRespository
import com.biprangshu.weatherapp.util.Resource
import com.biprangshu.weatherapp.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRespository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )

        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}