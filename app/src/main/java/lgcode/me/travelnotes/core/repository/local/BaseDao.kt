package lgcode.me.travelnotes.core.repository.local

interface BaseDao<T> {

    fun getAll(): List<T>

    fun getById(id: Int): T

    fun insert(input: T): Long?

    fun update(input: T): Int

    fun remove(input: T): Int
}