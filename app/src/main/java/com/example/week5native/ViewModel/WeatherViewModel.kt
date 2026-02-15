package com.example.week5native.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5native.data.remote.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _temperature = MutableStateFlow<Double?>(null)
    val temperature: StateFlow<Double?> = _temperature

    private val _cityName = MutableStateFlow("Helsinki")
    val cityName: StateFlow<String> = _cityName

    fun fetchWeather() {
        // Noudetaan Sää OpenWeatherin API-avaimen avulla kaupunkikohtaisesti
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(
                    city = _cityName.value,
                    apiKey = "OPENWEATHER_API_KEY"
                )
                _temperature.value = response.main.temp
            } catch (e: Exception) {
                e.printStackTrace()
                _temperature.value = null
            }
        }
    }


    fun setCity(name: String) {
        _cityName.value = name
    }
}
