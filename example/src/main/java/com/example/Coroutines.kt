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

//fun main() = runBlocking {
//	val stateFlow = MutableStateFlow(0)
//	val flow = stateFlow.asStateFlow()
//	repeat(10){
//		delay(300)
//		stateFlow.value = it
//	}
//	flow.collect {
//		println(it)
//	}
//
//	println("Collect done")
//}

fun main() = runBlocking {
	val stateFlow = MutableStateFlow(0)
	val flow = stateFlow.asStateFlow()
	
	// Launch a coroutine to collect the stateFlow values
	val job = launch {
		flow.collect {
			println(it)
		}
	}
	// TODO: WARNING If you remove delay(300), you will face a different issue because the flow will emit updates so quickly that it might not have the chance to process them properly within the collect block, especially if it is running on the same thread. In your example, without the delay, the flow updates might happen almost instantaneously, and you might not observe the intended behavior.
	// Update stateFlow values in the main coroutine
	repeat(10) {
		delay(300)
		stateFlow.value = it
	}
	
	// Cancel the collection coroutine after updates are done
	job.cancel()
	println("Collect done")
}