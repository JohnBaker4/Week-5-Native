package com.example.week5native.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5native.ViewModel.WeatherViewModel


@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {

    val temperature by viewModel.temperature.collectAsState()
    val cityName by viewModel.cityName.collectAsState()

    // Scaffold ja paddingValues auttaa tumman teeman kanssa, muuten näkyy valkoista
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
                    verticalArrangement = Arrangement . Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "City: $cityName", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (temperature != null) "Temperature: $temperature°C" else "Either the temperature is loading or it can't be found...",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.fetchWeather() }) {
                Text("Fetch Weather")
            }

            Text("Dark theme")
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onToggleTheme() }
            )
        }
    }
}