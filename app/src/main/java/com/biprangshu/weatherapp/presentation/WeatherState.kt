package com.biprangshu.weatherapp.presentation

import com.biprangshu.weatherapp.weather.WeatherInfo

data class WeatherState (
    val weatherInfo: WeatherInfo?=null,
    val isLoading: Boolean=false,
    val error: String?=null
)