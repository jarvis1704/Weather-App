package com.biprangshu.weatherapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biprangshu.weatherapp.R
import com.biprangshu.weatherapp.weather.WeatherDataDisplay
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    state: WeatherState,
    backgroundColor: androidx.compose.ui.graphics.Color,

) {

    state.weatherInfo?.currentWeatherData?.let {
        Card (
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(16.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Your Location", modifier = Modifier.align(Alignment.Start), color = Color.White)
                Text(text = "Today ${
                    it.time.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                }", modifier = Modifier.align(Alignment.End), color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Image(painter = painterResource(id = it.weatherType.iconRes), contentDescription = null, modifier = Modifier.width(200.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "${it.temperatureCelsius}°C", fontSize = 50.sp, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.weatherType.weatherDesc, fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(value = it.pressure.roundToInt(), unit = "hpa", icon = ImageVector.vectorResource(
                        id = R.drawable.ic_pressure,
                    ), iconTint = Color.White, TextStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(value = it.humidity.roundToInt(), unit = "%", icon = ImageVector.vectorResource(
                        id = R.drawable.ic_drop,
                    ), iconTint = Color.White, TextStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(value = it.windSpeed.roundToInt(), unit = "km/h", icon = ImageVector.vectorResource(
                        id = R.drawable.ic_wind,
                    ), iconTint = Color.White, TextStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }

}