import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Receiving prime numbers.")
        sendPrimes().collect {
            println("Received prime number $it")
        }
        println("Finished receiving numbers")
    }
}

fun sendPrimes(): Flow<Int> = flow {
    val primes = listOf(2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29)
    primes.forEach {
        delay(it * 100L)
        emit(it)
    }
}