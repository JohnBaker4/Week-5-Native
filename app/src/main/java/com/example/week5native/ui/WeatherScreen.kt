package com.example.week5native.ui

import android.R.attr.apiKey
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
import com.example.week5native.ViewModel.WeatherViewModel


@Composable
fun WeatherScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    viewModel: WeatherViewModel,
    apiKey: String,
) {

    val uiState by viewModel.uiState.collectAsState()
    val city by viewModel.city.collectAsState()

    // Scaffold ja paddingValues auttaa tumman teeman kanssa, muuten näkyy valkoista
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tiedot haetusta kaupungista tulee tällä näkyviin
            if (uiState.isLoading) {
                Text("Loading...")
                CircularProgressIndicator()
            } else if (uiState.error != null) {
                Text("Error: ${uiState.error}")
            } else {
                Text("City: ${uiState.cityName}")
                Text("Temp: ${uiState.temperature}°C")
                Text("Description: ${uiState.description}")
            }

            // Tekstikenttä johon kirjoitetaan kaupungin nimi
            OutlinedTextField(
                value = city,
                onValueChange = { viewModel.updateCity(it) },
                label = { Text("City") }
            )

            // Nappi sään hakemiselle
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.fetchWeather(apiKey)
            }) {
                Text("Fetch Weather")
            }

            // Vipu tummalle teemalle
            Spacer(modifier = Modifier.height(16.dp))
            Text("Dark theme")
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onToggleTheme() }
            )
        }
    }
}