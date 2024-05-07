package com.amonteiro.a2024_05_supvincimb

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amonteiro.a2024_05_supvincimb.model.WeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class PictureBean(val id: Int, val url: String, val title: String, val longText: String)

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""

class MainViewModel : ViewModel() {
    var pictureList: List<PictureBean> = ArrayList<PictureBean>()

    var pictureList2 = mutableStateOf<List<PictureBean>>(emptyList())

    init {//Création d'un jeu de donnée au démarrage
        loadFakeData()
    }

    fun loadRealData() {
        viewModelScope.launch(Dispatchers.Default) {


            val list = WeatherAPI.loadWeatherAround("Nice").map { weather ->
                PictureBean(
                    weather.id,
                    weather.weather.getOrNull(0)?.icon ?: "",
                    weather.name,
                    "Il fait ${weather.main.temp}° avec un vent de ${weather.wind.speed}m/s"
                )
            }

            launch(Dispatchers.Main) {

                pictureList2.value = list
            }
        }
    }

    fun loadFakeData() {
        pictureList = arrayListOf(
            PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }
}