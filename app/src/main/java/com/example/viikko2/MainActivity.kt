package com.example.viikko2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.viikko2.ui.HomeScreen
import com.example.viikko2.ui.theme.Viikko2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko2Theme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        title = "TODO APP",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

