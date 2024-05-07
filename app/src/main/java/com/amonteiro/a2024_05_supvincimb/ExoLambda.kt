package com.amonteiro.a2024_05_supvincimb

import kotlin.concurrent.thread

fun main() {
    exo3()
}

fun exo1() {

    val lower: (String) -> Unit = { it: String -> println(it.lowercase()) }
    val lower2 = { it: String -> println(it.lowercase()) }
    val lower3: (String) -> Unit = { it -> println(it.lowercase()) }
    val lower4: (String) -> Unit = { println(it.lowercase()) }

    lower("Coucou")

    val hour: (Int) -> Int = { it / 60 }
    println(hour(75))

    val max = { a: Int, b: Int -> Math.max(a, b) }
    println(max(7, 5))

    val reverse: (String) -> String = { it.reversed() }

    var minToMinHour: ((Int?) -> Pair<Int, Int>?)? = { if (it == null) null else Pair(it / 60, it % 60) }
    println(minToMinHour?.invoke(126))
    println(minToMinHour?.invoke(null))
    minToMinHour = null
}

data class UserBean(var name: String, var old: Int)

fun exo2() {
    val user = UserBean("Bob", 19)
    val user2 = UserBean("Toto", 45)
    val user3 = UserBean("Charles", 26)

    val compareUsersByName: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }
    val compareUsersByOld: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.old >= u2.old) u1 else u2 }

    var minName = compareUsersByName(user, user2)
    println(minName)

    var minOld = compareUsersByOld(user, user2)
    println(minOld)

    minName = compareUsers(user, user2, user3, compareUsersByName)
    println(minName)

    minOld = compareUsers(user, user2, user3, compareUsersByOld)
    println(minOld)

    val near30 = compareUsers(user, user2, user3) { u1, u2 -> if (Math.abs(u1.old - 30) <= Math.abs(u2.old - 30)) u1 else u2 }
    println(near30)

}

fun compareUsers(u1: UserBean, u2: UserBean, u3: UserBean, comparator: (UserBean, UserBean) -> UserBean) = comparator(comparator(u1, u2), u3)

data class PersonBean(var name: String, var note: Int)

fun exo3() {
    val list = arrayListOf(
        PersonBean("toto", 16),
        PersonBean("Tata", 20),
        PersonBean("Toto", 8),
        PersonBean("Charles", 14)
    )

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >= 10 }.joinToString("\n") { "-${it.name} : ${it.note}" })

    //TODO
    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    val isToto: (PersonBean) -> Boolean = { it.name.equals("Toto", true) }
    println(list.count(isToto))

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.count { it.note >= 10 && isToto(it) })

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val average = list.map { it.note}.average()
    println(list.count { it.note >= average && isToto(it) })

    println("\n\nAfficher la list triée par nom sans doublon")
    println(list.distinctBy { it.name.uppercase() }.sortedBy { it.name.lowercase() })

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.filter {  it.note <10  }.forEach { it.note++ }

    println("\n\nAjouter un point à tous les Toto")
    list.filter(isToto).forEach { it.note++ }

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    val minValue = list.minOf { it.note }
    list.removeIf { it.note == minValue }

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    println(list.filter {  it.note >10  }.sortedBy { it.name }.map { it.name })

    //TODO Créer une variable isToto contenant une lambda qui teste si un PersonBean s'appelle Toto

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    list.map { PersonBean(it.name, it.note) }
    list.map { it.copy() }

    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")

    thread {

    }
}



