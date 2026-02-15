package com.example.week5native

import android.R.attr.apiKey
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5native.BuildConfig.OPENWEATHER_API_KEY
import com.example.week5native.ui.WeatherScreen
import com.example.week5native.ui.theme.Week5NativeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Otin tumman teeman viime tehtävästä siltä varalta että valkoinen on palavaa magnesiumia
            var isDarkTheme by remember { mutableStateOf(true) }

            Week5NativeTheme (darkTheme = isDarkTheme) {
                WeatherScreen(
                    isDarkTheme = isDarkTheme,
                    viewModel = viewModel(),
                    onToggleTheme = {
                        isDarkTheme = !isDarkTheme
                    },
                    apiKey = OPENWEATHER_API_KEY
                )
                }
            }
        }
    }
