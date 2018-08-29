package com.karavaevitalii.maybe

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MaybeTest {
    @Test
    fun createJust() {
        val a = just(42)

        assertEquals(true, a.isPresent, "Just isPresent")
        assertEquals(42, a.value, "Just value")
    }

    @Test
    fun createEmpty() {
        val a = empty()

        assertEquals(false, a.isPresent, "Empty isPresent")
    }

    @Test
    fun createJustAsMaybe() {
        val a: Maybe<String> = just("42")

        assertEquals(true, a.isPresent, "Just as Maybe isPresent")
        assertEquals("42", (a as Just<String>).value, "Just as Maybe value")
    }

    @Test
    fun createEmptyAsMaybe() {
        val a: Maybe<Nothing> = empty()

        assertEquals(false, a.isPresent, "Empty as Maybe isPresent")
    }

    @Test
    fun whenExpressionJust() {
        fun <T> foo(maybe: Maybe<T>) = when (maybe) {
            is Just -> assertEquals(3.14, maybe.value, "Just value in when expression")
            is Empty -> fail("Just as Maybe in when expression")
        }

        val a = just(3.14)
        foo(a)
    }

    @Test
    fun whenExpressionEmpty() {
        fun <T> foo(maybe: Maybe<T>) = when (maybe) {
            is Just -> fail("Empty as Maybe in when expression")
            is Empty -> assertEquals(false, maybe.isPresent, "Empty value in when expression")
        }

        val a = empty()
        foo(a)
    }

    @Test
    fun justCopy() {
        val a = just(listOf(1, 2, 3))
        val b = a.copy()

        assertEquals(a, b, "Just copy")
    }

    @Test
    fun emptyValue() {
        fun <T> foo(maybe: Maybe<T>) = when (maybe) {
            is Just -> fail("Empty is not Just")
            Empty -> maybe.value
        }

        val a = empty()
        assertThrows<MaybeException>("Value from empty instance should throw") { foo(a) }
    }
}