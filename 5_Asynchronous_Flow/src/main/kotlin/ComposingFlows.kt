import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        zip()
        combine()
    }
}

suspend fun zip() {
    val english = flowOf("One", "Two", "Three")
    val french = flowOf("Un", "Deux", "Trois")

    english.zip(french) {a, b ->
        "'$a' in french is '$b'"
    }.collect {
        println(it)
    }
}

suspend fun combine() {
    val numbers = (1..5).asFlow()
        .onEach {
            delay(300L)
        }
    val letters = flowOf("A", "B", "C", "D", "E")
        .onEach {
            delay(400L)
        }
    numbers.combine(letters) {n, l ->
        "'$n' -> '$l'"
    }.collect {
        println(it)
    }

}