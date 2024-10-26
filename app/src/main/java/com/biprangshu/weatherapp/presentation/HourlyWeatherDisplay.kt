package com.biprangshu.weatherapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.biprangshu.weatherapp.weather.WeatherData
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherDisplay(
    modifier: Modifier = Modifier,
    weatherData: WeatherData,
    textColor: Color= Color.White,

) {
    val formattedTime = remember(weatherData){
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = formattedTime, color = Color.LightGray)
        Image(painter = painterResource(id = weatherData.weatherType.iconRes), contentDescription = null, modifier = Modifier.size(40.dp))
        Text(text = "${weatherData.temperatureCelsius}°C", color = textColor, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)

    }
}