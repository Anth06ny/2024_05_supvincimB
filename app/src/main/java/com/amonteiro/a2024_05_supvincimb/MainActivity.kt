package com.amonteiro.a2024_05_supvincimb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.amonteiro.a2024_05_supvincimb.ui.screen.SearchScreen
import com.amonteiro.a2024_05_supvincimb.ui.theme._2024_05_supvincimBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2024_05_supvincimBTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                  SearchScreen(MainViewModel())
                }
            }
        }
    }
}
