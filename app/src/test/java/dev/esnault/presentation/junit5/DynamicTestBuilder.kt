package dev.esnault.presentation.junit5

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class DynamicTestsBuilder {
    val tests = mutableListOf<DynamicTest>()

    fun test(name: String, block: () -> Unit) {
        val safeName = if (name.isBlank()) "\"$name\"" else name
        tests.add(DynamicTest.dynamicTest(safeName, block))
    }

    fun testTrue(name: String, actual: () -> Boolean) = test(name) { assertTrue(actual()) }

    fun testTrue(name: String, actual: Boolean) = test(name) { assertTrue(actual) }

    fun testFalse(name: String, actual: () -> Boolean) = test(name) { assertFalse(actual()) }

    fun testFalse(name: String, actual: Boolean) = test(name) { assertFalse(actual) }

    fun <T> testEquals(name: String, expected: T, actual: () -> T) =
        test(name) { assertEquals(expected, actual()) }

    fun <T> testEquals(name: String, expected: T, actual: T) =
        test(name) { assertEquals(expected, actual) }

    fun <T> testNotEquals(name: String, expected: T, actual: () -> T) =
        test(name) { assertNotEquals(expected, actual()) }

    fun <T> testNotEquals(name: String, expected: T, actual: T) =
        test(name) { assertNotEquals(expected, actual) }

    inline fun <reified T : Throwable> testThrows(name: String, crossinline block: () -> Unit) =
        test(name) { assertThrows<T>(block) }
}

fun dynamicTests(init: DynamicTestsBuilder.() -> Unit) : List<DynamicTest> {
    val builder = DynamicTestsBuilder()
    builder.init()
    return builder.tests
}