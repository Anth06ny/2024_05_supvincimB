package com.amonteiro.a2024_05_supvincimb

fun main() {
    var text = MyLiveData()
    text.value = "Coucou"

    text.observator = {
        println("it=$it")
    }

    text.value = "Hello"
}

class MyLiveData {

    var value :String? = null
        set(newValue){
            field = newValue
            observator?.invoke(newValue)
        }

    var observator : ((String?)->Unit)? = null

}