package com.biprangshu.weatherapp.domain.respository

import com.biprangshu.weatherapp.util.Resource
import com.biprangshu.weatherapp.weather.WeatherInfo

interface WeatherRespository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}