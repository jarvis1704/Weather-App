package com.biprangshu.weatherapp.weather

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDataDisplay(
    modifier: Modifier = Modifier,
    value: Int,
    unit: String,
    icon: ImageVector,
    iconTint: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    TextStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(),
    ) {
        Row(
            modifier= modifier,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(25.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "$value$unit", style = TextStyle)
        }
}