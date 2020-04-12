import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.IndexOutOfBoundsException

fun main() {
    runBlocking {

        val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception handled: ${throwable.localizedMessage}")
        }
        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException("Exception in coroutine")
        }
        job.join()

        val deferred = GlobalScope.async {
            println("Throwing exception from async")
            throw ArithmeticException("Exception from async")
        }

        try {
            deferred.await()
        } catch (e: Exception) {
            println("Caught arithmetic exception: ${e.localizedMessage}")
        }
    }
}