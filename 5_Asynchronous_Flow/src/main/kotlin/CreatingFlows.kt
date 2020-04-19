import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        sendNumbers().collect {
            println("Number: $it")
        }
    }
}

fun sendNumbers() = flowOf(1, 2, "three")

//fun sendNumbers(): Flow<Int> =
//    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow()

//fun sendNumbers(): Flow<Int> = flow {
//    for (i in 1..10) {
//        emit(i)
//    }
//}