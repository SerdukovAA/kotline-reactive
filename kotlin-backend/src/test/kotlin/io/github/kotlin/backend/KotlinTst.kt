package io.github.kotlin.backend

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class KotlinTst {

    @Test
    fun coroutine() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L)
    }

    @Test
    fun coroutine3() {
        runBlocking {
            this.launch {  job() }
            this.launch {  job2() }
        }
    }

    private suspend fun job() {
            // launch a new coroutine and keep a reference to its Job
            delay(3000L)
            println("World!")
    }

    private suspend fun job2() {
            // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            println("World!")
    }



    @Test
    fun coroutine2() {
        runBlocking {
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L)
    }

}