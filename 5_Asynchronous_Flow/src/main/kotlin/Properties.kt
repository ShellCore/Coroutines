import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
        val numbersFlow = sendNewNumbers()
        println("Flow hasn't started yet.")
        println("Starting flow now")
        numbersFlow.collect {
            println(it)
        }
    }

    runBlocking {
        val numbersFlowDelayed = sendNumbersDelayed()
        println("Flow hasn't started yet.")
        println("Starting flow now")
        withTimeoutOrNull(1000) {
            numbersFlowDelayed.collect {
                println(it)
            }
        }
    }
}

fun sendNewNumbers() = listOf(1, 2, 3).asFlow()

fun sendNumbersDelayed() = flow {
    val list = listOf(1,2,3)
    list.forEach {
        delay(400L)
        emit(it)
    }
}