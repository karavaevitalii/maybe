package com.karavaevitalii.maybe

fun <T> just(value: T) = Just(value)

fun empty() = Empty

sealed class Maybe<T> {
    abstract val isPresent: Boolean
    abstract val value: T
}

data class Just<T> internal constructor(override val value: T, override val isPresent: Boolean = true) : Maybe<T>()

object Empty : Maybe<Nothing>() { //Nothing is kotlin class thus it is Empty
    override val value get() = throw MaybeException("Can not get value from empty instance.")
    override val isPresent = false
}

class MaybeException(override val message: String) : RuntimeException(message)