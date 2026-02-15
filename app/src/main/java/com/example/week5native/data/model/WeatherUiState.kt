package com.example.week5native.data.model

data class WeatherUiState(
    val temperature: Double? = null,
    val cityName: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)