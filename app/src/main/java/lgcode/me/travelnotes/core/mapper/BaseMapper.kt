package lgcode.me.travelnotes.core.mapper

interface BaseMapper<T: Any, P: Any> {
    fun mapTo(input: T): P
    fun mapFrom(input: P): T
}