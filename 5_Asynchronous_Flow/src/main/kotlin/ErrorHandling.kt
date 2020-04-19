import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        tryCatch()
//        catchOperator()
        onCompletionOperator()
    }
}

suspend fun tryCatch() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .collect { println(it) }
    } catch (e: Exception) {
        println("Error: $e")
    }
}

suspend fun catchOperator() {
    (1..3).asFlow()
        .onEach {  check(it != 2) }
        .catch { e -> println("Error: $e") }
        .collect { println(it) }
}

suspend fun onCompletionOperator() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed with $cause")
            } else {
                println("Flow completed successfully")
            }
        }
        .catch { e -> println("Error: $e") }
        .collect { println(it) }
}