package dev.esnault.presentation.junit5

import org.junit.jupiter.api.*
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class ColorTest {

    @Nested
    @DisplayName("toString()")
    inner class ToString {

        @Test
        @DisplayName("should return #000000 for rgb(0, 0, 0)")
        fun `#000000`() {
            val color = Color(0, 0, 0)
            val result = color.toString()
            assertEquals(expected = "#000000", actual = result)
        }

        @Test
        @DisplayName("should return #FFFFFF for rgb(255, 255, 255)")
        fun `#FFFFFF`() {
            val color = Color(255, 255, 255)
            val result = color.toString()
            assertEquals(expected = "#FFFFFF", actual = result)
        }

        @Test
        @DisplayName("should return #123456 for rgb(18, 52, 86)")
        fun `#123456`() {
            val color = Color(18, 52, 86)
            val result = color.toString()
            assertEquals(expected = "#123456", actual = result)
        }
    }

    @Nested
    @DisplayName("fromString()")
    inner class FromString {

        @Nested
        inner class Valid {

            @Test
            @DisplayName("should parse #000000 as rgb(0, 0, 0)")
            fun `#000000`() {
                val colorString = "#000000"
                val result = Color.fromString(colorString)
                val expected = Color(0, 0, 0)
                assertEquals(expected = expected, actual = result)
            }

            @Test
            @DisplayName("should parse #FFFFFF as rgb(255, 255, 255)")
            fun `#FFFFFF`() {
                val colorString = "#FFFFFF"
                val result = Color.fromString(colorString)
                val expected = Color(255, 255, 255)
                assertEquals(expected = expected, actual = result)
            }

            @Test
            @DisplayName("should parse #123456 as rgb(18, 52, 86)")
            fun `#123456`() {
                val colorString = "#123456"
                val result = Color.fromString(colorString)
                val expected = Color(18, 52, 86)
                assertEquals(expected = expected, actual = result)
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