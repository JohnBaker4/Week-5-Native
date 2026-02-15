package com.example.week5native.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5native.data.model.WeatherResponse
import com.example.week5native.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.week5native.data.model.WeatherUiState
import com.example.week5native.data.remote.WeatherApi

class WeatherViewModel(private val api: WeatherApi = RetrofitInstance.api) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    // Kaupungin nimi StateFlowksi jotta Compose reagoi muutoksiin
    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    fun updateCity(newCity: String) {
        _city.value = newCity
    }

    // Noudetaan Sää OpenWeatherin API-avaimen avulla kaupunkikohtaisesti
    fun fetchWeather(apiKey: String) {
        if (city.value.isBlank()) return

        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        //
        viewModelScope.launch {
            try {
                val response: WeatherResponse = api.getWeather(_city.value, apiKey)
                _uiState.value = WeatherUiState(
                    temperature = response.main.temp,
                    cityName = response.name,
                    description = response.weather.firstOrNull()?.description ?: "",
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }
}


