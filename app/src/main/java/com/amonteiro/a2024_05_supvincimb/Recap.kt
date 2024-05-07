package com.amonteiro.a2024_05_supvincimb

fun main()  {
    //Déclaraiton d'une variable
    var toto = StudentBean("Toto")
    toto.name = "Tata"
    toto.mail = null

    //Safe check + elvis operator
    println(toto.mail?.uppercase() ?: "-")

    //Idem mais nullable
    var totoNullable: StudentBean? = StudentBean("Toto")

    toto(b = "ddz", a = 5) // toto(5, "ddz")
    toto(b = "ddz") // toto(0, "ddz")

    "".also {

    }
}


//Class
class StudentBean(var name: String) {

    var mail: String? = ""
        set(value) {
            field = value
        }
        get() {
            return field
        }
}

//fonction classique
fun toto(a: Int = 0, b: String = "-"): Int {
    println("a=$a")
    println("b=$b")
    return a
}

//fonction expression avec if expression
fun max(a: Int, b: Int) = if (a <= b) a else b





