package dev.esnault.presentation.junit5

import org.junit.jupiter.api.*
import java.lang.IllegalArgumentException

class ColorTest {

    val testMapping = listOf<Pair<String, Color>>(
        "#000000" to Color(0, 0, 0),
        "#FFFFFF" to Color(255, 255, 255),
        "#123456" to Color(18, 52, 86),
    )

    @TestFactory
    @DisplayName("toString()")
    fun toStringTests() = dynamicTests {
        testMapping.forEach { (colorString, color) ->
            testEquals(
                name = "should return $colorString for rgb${color.toRgbString()}",
                expected = colorString,
                actual = color.toString(),
            )
        }
    }

    @Nested
    @DisplayName("fromString()")
    inner class FromString {

        @TestFactory
        @DisplayName("Valid")
        fun validTests() = dynamicTests {
            testMapping.forEach { (colorString, color) ->
                testEquals(
                    name = "should parse $colorString as rgb${color.toRgbString()}",
                    expected = color,
                    actual = Color.fromString(colorString),
                )
            }
        }

        @TestFactory
        @DisplayName("Invalid")
        fun invalidTests() = dynamicTests {
            fun test(name: String, input: String) {
                testThrows<IllegalArgumentException>(name = "should throw for: $name") {
                    Color.fromString(input)
                }
            }

            test(name = "empty string", input = "")
            test(name = "invalid format (no #)", input = "FFFFFF")
            test(name = "invalid format (too short)", input = "#FFF")
            test(name = "invalid format (too long)", input = "#FFFFFFFF")
            test(name = "invalid format (not hex)", input = "#NOTHEX")
        }
    }
}