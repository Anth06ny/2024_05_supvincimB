package com.amonteiro.a2024_05_supvincimb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.amonteiro.a2024_05_supvincimb.ui.theme._2024_05_supvincimBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2024_05_supvincimBTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                  // SearchScreen(MainViewModel())
                    TestShareRemember()
                }
            }
        }
    }
}

@Composable
fun TestShareRemember() {
    var expanded = remember { mutableStateOf(false) }

    Column {
        ElevatedButton(
            onClick = { expanded.value = !expanded.value }
        ) {
            Text(if (expanded.value) "Show less" else "Show more")
        }

        ElevatedButton(
            onClick = { expanded.value = !expanded.value },
        ) {
            Text(if (expanded.value) "Show less" else "Show more")
        }
        MyButton(expanded)
        MyButton()
    }
}
//Permet d'écouter l'état en dehors de la méthode
@Composable
fun MyButton(expanded: MutableState<Boolean> = remember { mutableStateOf(false) }) {

    ElevatedButton(
        onClick = { expanded.value = !expanded.value },
    ) {
        Text(if (expanded.value) "Show less" else "Show more")
    }
}
