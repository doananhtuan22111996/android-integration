package com.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

//fun main() = runBlocking {
//    val totalTime = measureTimeMillis {
//        val delay1 = async { delayFunction1() }
//        val delay2 = async { delayFunction2() }
//        val result1 = delay1.await()
//        val result2 = delay2.await()
//        println("Total time taken: ${result1 + result2}ms")
//    }
//    println("Done")
//}

//fun main() = runBlocking {
//    val job = launch {
//        repeat(10) {
//            println(it)
//            Thread.sleep(500)
//        }
//    }
//    delay(1300L)
//    job.cancel()
//    println("Quit")
//}

suspend fun delayFunction1(): Long {
	val delayTime = 1000L
	delay(delayTime)
	return delayTime
}

suspend fun delayFunction2(): Long {
	val delayTime = 500L
	delay(delayTime)
	return delayTime
}

fun main() = runBlocking {
	val stateFlow = MutableStateFlow(0)
	val flow = stateFlow.asStateFlow()
	launch {
		val a = flow {
			emit(1)
			delay(500)
			emit(2)
			delay(500)
			emit(3)
		}.flowOn(Dispatchers.IO)
		withContext(Dispatchers.Main) {
			a.collect {
				println("Collect 2: $it")
				stateFlow.value = it
			}
		}
	}
	println("Collect done")
}