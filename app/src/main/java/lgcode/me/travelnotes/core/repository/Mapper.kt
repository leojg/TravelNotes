package me.lgcode.balance.core.repository

interface Mapper<I, O> {

    fun mapTo(input: I?): O?

    fun mapFrom(input: O?): I?

}