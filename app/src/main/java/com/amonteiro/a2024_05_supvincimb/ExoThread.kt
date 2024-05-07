package com.amonteiro.a2024_05_supvincimb

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    exoCoroutine(1_000_000)
}

fun exoCoroutine(callNumber: Int) {
    val start = System.currentTimeMillis()
    val box = BallotBoxBean()

    runBlocking {
        repeat(callNumber) {
            launch {
                box.add1VoiceAndWaitWithDelay()
            }
        }
    }

    println("Number : " + box.current)
    println("Done in ${(System.currentTimeMillis() - start) / 1000} seconds")
}

fun exoThread() {
    val ballot = BallotBoxBean()
    val before = System.currentTimeMillis()

    val listThread = ArrayList<Thread>()

    runBlocking {
        launch { }
    }

    repeat(100000) {
        listThread += thread {
            ballot.add1VoiceAndWait()
        }
    }
    println("Attente...")
    listThread.forEach { it.join() }

    println("number=${ballot.current}")
    val after = System.currentTimeMillis()
    println("Done in ${after - before} ms")
}

//Classe garantissant un compte ThreadSafe
class BallotBoxBean {
    private val number = AtomicInteger(0)


    suspend fun add1VoiceAndWaitWithDelay() {
        delay(2000)
        number.incrementAndGet()
    }

    fun add1VoiceAndWait() {
        Thread.sleep(2000)
        number.incrementAndGet()
    }

    val current
        get() = number.get()
}