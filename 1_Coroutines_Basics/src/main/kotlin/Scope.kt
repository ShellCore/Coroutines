import kotlinx.coroutines.*

fun main() {

    println("Program execution will now block")
    runBlocking {
        launch {
            delay(1000L)
            println("Task from runblocking")
        }

        GlobalScope.launch {
            delay(500L)
            println("Task from GlobalScope")
        }

        coroutineScope {
            delay(1500L)
            println("Task from CoroutineScope")
        }
    }
    println("Program execution will now continue")
}
