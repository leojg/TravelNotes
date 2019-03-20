package lgcode.me.travelnotes.core.usecase

import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import lgcode.me.travelnotes.core.repository.Result

abstract class UseCase<out T, in P> where T: Any {
    abstract suspend fun run(params: P?): Result<T>
    operator fun invoke(params: P? = null, onResult: (Result<T>) -> Unit = {}) {
        val job = GlobalScope.async { run(params) }
        GlobalScope.launch(Main) { onResult(job.await())}
    }
}