package com.karavaevitalii.maybe

fun <T> just(value: T) = Just(value)

fun empty() = Empty

sealed class Maybe<T> {
    abstract val isPresent: Boolean
}

data class Just<T> internal constructor(val value: T) : Maybe<T>() {
    override val isPresent = true
}

object Empty : Maybe<Any>() { //Nothing is kotlin class thus it is Empty
    override val isPresent = false
}