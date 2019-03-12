package me.lgcode.balance.core.repository.local

interface BaseDao<T> {

    fun getAll(): List<T>

    fun get(): T

    fun insert(input: T): Long?

    fun remove(input: T): Int

    fun update(input: T): Int
}