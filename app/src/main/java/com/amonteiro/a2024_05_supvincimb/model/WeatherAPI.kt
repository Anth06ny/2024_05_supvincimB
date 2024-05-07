package com.amonteiro.a2024_05_supvincimb.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    var res = WeatherAPI.loadWeatherAround("Nice")
    println(res)
}

object WeatherAPI {

    //Attribut instancié 1 seule fois car c'est un singleton
    //Et uniquement à la 1er utilisation (Lazy Loading)
    private val client = OkHttpClient()
    val gson = Gson()

    fun loadWeatherAround(cityName: String): List<WeatherBean> {
        //Réaliser la requête.
        val json: String = sendGet("https://api.openweathermap.org/data/2.5/find?q=$cityName&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        //Parser le JSON avec le bon bean et GSON
        val data  = gson.fromJson(json, WeatherAroundResult::class.java)

        //On met directement l'URL de l'image plutôt que le nom de l'icône dans icon
        //V1
        data.list.forEach{
            it.weather.getOrNull(0)?.icon = "https://openweathermap.org/img/wn/${it.weather.getOrNull(0)?.icon}@4x.png"
        }
        //Retourner la donnée
        return data.list

        //Version plus compacte
//        return data.list.onEach{
//            it.weather.getOrNull(0)?.apply {
//                icon = "https://openweathermap.org/img/wn/${icon}@4x.png"
//            }
//        }
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()

        //Execution de la requête
        return client.newCall(request).execute().use{ //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

/* -------------------------------- */
// API Weather
/* -------------------------------- */

data class WeatherAroundResult(var list : List<WeatherBean>)

data class WeatherBean(var id:Int, var main : TempBean,var wind : WindBean,var name : String, var weather : List<DescriptionBean>)
data class TempBean(var temp: Double)
data class WindBean(var speed: Double)

data class DescriptionBean(
    var description: String,
    var icon: String,
    var main: String
)
