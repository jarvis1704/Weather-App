package com.biprangshu.weatherapp.weather

data class WeatherInfo(
    val WeatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
