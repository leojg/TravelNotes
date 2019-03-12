package me.lgcode.balance.core.repository

/**
 * Contains the result from repository layer.
 */
sealed class Result<out T: Any> {
    class Success<out T: Any>(val value: T): Result<T>()
    class Failure(val exception: Throwable): Result<Nothing>()
}